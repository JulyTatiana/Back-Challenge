package com.sofka.demo.useCases;

import com.sofka.demo.dtos.DTOproduct;
import com.sofka.demo.mappers.ProductMapper;
import com.sofka.demo.repositories.IntProductRepository;
import org.springframework.stereotype.Service;
//import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

//import javax.validation.Valid;

@Service

public class CreateProductUseCase {

    private final IntProductRepository repository;
    private final ProductMapper mapper;

    public CreateProductUseCase(IntProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public boolean validateAttributes(DTOproduct productDTO){
        return productDTO.getProductName() != null &&
                productDTO.getProductPrice() != null &&
                productDTO.getProviderId() != null &&
                productDTO.getAvailableUnits() != null &&
                productDTO.getMaxQuantity() != null &&
                productDTO.getMinQuantity() != null;
    }

    private Mono<DTOproduct> validateProduct(DTOproduct productDTO){
        return Mono.just(productDTO)
                .filter(productDTO1 -> validateAttributes(productDTO1))
                .switchIfEmpty(Mono.error(() -> new Exception("There are missing attributes")));
    }

    public Mono<DTOproduct> createProduct(DTOproduct productDTO){
        return validateProduct(productDTO)
                .flatMap(productDTO1 -> repository.save(mapper.toProduct(productDTO1)))
                .map(product -> mapper.toProductDTO(product));
    }
}
package com.sofka.demo.useCases;

import com.sofka.demo.dtos.DTOproduct;
import com.sofka.demo.mappers.ProductMapper;
import com.sofka.demo.repositories.IntProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetOneProductUseCase {

    private final IntProductRepository repository;
    private final ProductMapper mapper;


    public GetOneProductUseCase(IntProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<DTOproduct> getProduct(String productId){
        return repository.findById(productId).map(product -> mapper.toProductDTO(product))
                .switchIfEmpty(Mono.error(() -> new Exception("It wasn't able to find the provider with the given ID")));
    }
}

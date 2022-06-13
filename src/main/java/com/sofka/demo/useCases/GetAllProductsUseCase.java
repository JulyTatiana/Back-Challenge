package com.sofka.demo.useCases;

import com.sofka.demo.dtos.DTOproduct;
import com.sofka.demo.mappers.ProductMapper;
import com.sofka.demo.repositories.IntProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetAllProductsUseCase {

    private final IntProductRepository repository;
    private final ProductMapper mapper;

    public GetAllProductsUseCase(IntProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Flux<DTOproduct> getAllProducts(){
        return repository.findAll().map(product -> mapper.toProductDTO(product));
    }

}

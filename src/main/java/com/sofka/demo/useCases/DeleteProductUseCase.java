package com.sofka.demo.useCases;

import com.sofka.demo.repositories.IntProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteProductUseCase {

    private final IntProductRepository repository;

    public DeleteProductUseCase(IntProductRepository repository) {
        this.repository = repository;
    }

    public Mono<Void> deleteProduct(String id){
        return repository.findById(id)
                .switchIfEmpty(Mono.error(() -> new Exception("Not able to find a product with the given id")))
                .flatMap(product -> repository.deleteById(product.getProductId()));
    }
}

package com.sofka.demo.useCases;

import com.sofka.demo.collections.Provider;
import com.sofka.demo.repositories.IntProviderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteProviderUseCase{
    private final IntProviderRepository repository;

    public DeleteProviderUseCase(IntProviderRepository repository){
        this.repository = repository;
    }

    private Mono<Provider> findProviderById(String id){
        return repository.findById(id)
                .switchIfEmpty(Mono.error(()-> new Throwable("With the provider ID given wasn't possible to find the provider")));
    }

    public Mono<Void> deleteProviderById(String id){
        return findProviderById(id)
                .flatMap(provider -> repository.deleteById(provider.getProviderId()));
    }
}

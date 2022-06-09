package com.sofka.demo.usecases;

import com.sofka.demo.collections.Provider;
import com.sofka.demo.repository.IProviderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteProviderUseCase{
    private final IProviderRepository repository;

    public DeleteProviderUseCase(IProviderRepository repository){
        this.repository = repository;
    }

    private Mono<Provider> findProviderById(String id){
        return repository.findById(id)
                .switchIfEmpty(Mono.error(()-> new Throwable("Not able to find a provider with the given id")));
    }

    public Mono<Void> deleteProviderById(String id){
        return findProviderById(id)
                .flatMap(provider -> repository.deleteById(provider.getProviderId()));
    }
}

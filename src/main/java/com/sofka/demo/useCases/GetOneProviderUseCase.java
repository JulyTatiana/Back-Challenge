package com.sofka.demo.useCases;

import com.sofka.demo.dtos.DTOprovider;
import com.sofka.demo.mappers.ProviderMapper;
import com.sofka.demo.repositories.IntProviderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetOneProviderUseCase {

    private final IntProviderRepository repository;
    private final ProviderMapper mapper;

    public GetOneProviderUseCase(IntProviderRepository repository, ProviderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<DTOprovider> getProvider(String providerName){
        return repository.findByproviderName(providerName).map(provider -> mapper.toProviderDTO(provider))
                .switchIfEmpty(Mono.error(() -> new Exception("It wasn't possible with the given name to find the Provider")));
    }
}

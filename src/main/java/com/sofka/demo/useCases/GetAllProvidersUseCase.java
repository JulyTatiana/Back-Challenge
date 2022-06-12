package com.sofka.demo.useCases;

import com.sofka.demo.dtos.DTOprovider;
import com.sofka.demo.mappers.ProviderMapper;
import com.sofka.demo.repositories.IntProviderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetAllProvidersUseCase {

    private final IntProviderRepository repository;
    private final ProviderMapper mapper;


    public GetAllProvidersUseCase(IntProviderRepository repository, ProviderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Flux<DTOprovider> getAllProviders(){
        return repository.findAll().map(mapper::toProviderDTO);
        //return repositories.findAll().map(provider ->mappers.toProviderDTO(provider));
    }
}

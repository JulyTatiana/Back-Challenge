package com.sofka.demo.usecases;

import com.sofka.demo.DTO.ProviderDTO;
import com.sofka.demo.mapper.ProviderMapper;
import com.sofka.demo.repository.IProviderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetProviderUseCase {

    private final IProviderRepository repository;
    private final ProviderMapper mapper;

    public GetProviderUseCase(IProviderRepository repository, ProviderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mono<ProviderDTO> getProvider(String providerName){
        return repository.findByproviderName(providerName).map(provider -> mapper.toProviderDTO(provider));
    }
}

package com.sofka.demo.usecases;

import com.sofka.demo.DTO.ProviderDTO;
import com.sofka.demo.mapper.ProviderMapper;
import com.sofka.demo.repository.IProviderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetProvidersUseCase {

    private final IProviderRepository repository;
    private final ProviderMapper mapper;


    public GetProvidersUseCase(IProviderRepository repository, ProviderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Flux<ProviderDTO> getAllProviders(){
        return repository.findAll().map(provider ->mapper.toProviderDTO(provider));
    }
}

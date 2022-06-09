package com.sofka.demo.usecases;

import com.sofka.demo.DTO.ProviderDTO;
import com.sofka.demo.mapper.ProviderMapper;
import com.sofka.demo.repository.IProviderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.awt.*;

@Service
public class CreateProviderUseCase {

    private final IProviderRepository repository;
    private final ProviderMapper mapper;

    public CreateProviderUseCase(IProviderRepository repository, ProviderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    //Validation 1
    private  boolean validateAttributes(ProviderDTO providerDTO){
        return providerDTO.getProviderName() != null &&
                providerDTO.getProviderAddress() != null &&
                providerDTO.getProviderIdentification() != null;
    }

    //Validation 2: call validation 1
    private Mono<ProviderDTO> validateProviderDTO(ProviderDTO providerDTO){
        return Mono.just(providerDTO)
                .filter(providerDTO1 -> this.validateAttributes(providerDTO1))
                .switchIfEmpty(Mono.error(() -> new Exception("Missing Attributes")));
    }

    //Validation 3: call validation 2
    public Mono<ProviderDTO> createProvider(ProviderDTO providerDTO){
        return validateProviderDTO(providerDTO)
                //flatMap flats the Mono<Mono>  into only 1 Mono(avoiding one insider another)
                .flatMap(providerDTO1 -> repository.save(mapper.toProvider(providerDTO1)))
                .map(provider -> mapper.toProviderDTO(provider));
    }
}

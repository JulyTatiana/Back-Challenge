package com.sofka.demo.useCases;

import com.sofka.demo.dtos.DTOprovider;
import com.sofka.demo.mappers.ProviderMapper;
import com.sofka.demo.repositories.IntProviderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class CreateProviderUseCase {

    private final IntProviderRepository repository;
    private final ProviderMapper mapper;

    public CreateProviderUseCase(IntProviderRepository repository, ProviderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    //Validation 1
    private  boolean validateAttributes(DTOprovider DTOprovider){
        return DTOprovider.getProviderName() != null &&
                DTOprovider.getProviderAddress() != null &&
                DTOprovider.getProviderIdentification() != null;
    }

    //Validation 2: call validation 1
    private Mono<DTOprovider> validateProviderDTO(DTOprovider DTOprovider){
        return Mono.just(DTOprovider)
                .filter(providerDTO1 -> this.validateAttributes(providerDTO1))
                .switchIfEmpty(Mono.error(() -> new Exception("Missing Attributes")));
    }

    //Validation 3: call validation 2
    public Mono<DTOprovider> createProvider(DTOprovider DTOprovider){
        return validateProviderDTO(DTOprovider)
                //flatMap flats the Mono<Mono>  into only 1 Mono(avoiding one insider another)
                .flatMap(providerDTO1 -> repository.save(mapper.toProvider(providerDTO1)))
                .map(provider -> mapper.toProviderDTO(provider));
    }
}

package com.sofka.demo.mapper;

import com.sofka.demo.DTO.ProviderDTO;
import com.sofka.demo.collections.Provider;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux

public class ProviderMapper {

    private final ModelMapper mapper;

    public ProviderMapper(ModelMapper mapper){
        this.mapper = mapper;
    }

    public ProviderDTO toProviderDTO(Provider provider){
        return mapper.map(provider, ProviderDTO.class);
    }

    public Provider toProvider(ProviderDTO providerDTO){
        return mapper.map(providerDTO, Provider.class);
    }
}

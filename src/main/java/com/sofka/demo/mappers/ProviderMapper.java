package com.sofka.demo.mappers;

import com.sofka.demo.dtos.DTOprovider;
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

    public DTOprovider toProviderDTO(Provider provider){
        return mapper.map(provider, DTOprovider.class);
    }

    public Provider toProvider(DTOprovider DTOprovider){
        return mapper.map(DTOprovider, Provider.class);
    }
}

package com.sofka.demo.mappers;

import com.sofka.demo.collections.Product;
import com.sofka.demo.dtos.DTOproduct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class ProductMapper {

    private final ModelMapper mapper;

    public ProductMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public DTOproduct toProductDTO(Product provider){
        return mapper.map(provider, DTOproduct.class);
    }

    public Product toProduct(DTOproduct DTOprovider){
        return mapper.map(DTOprovider, Product.class);
    }
}

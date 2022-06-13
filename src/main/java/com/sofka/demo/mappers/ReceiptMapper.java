package com.sofka.demo.mappers;

import com.sofka.demo.collections.Receipt;
import com.sofka.demo.dtos.DTOreceipt;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class ReceiptMapper {

    private final ModelMapper mapper;

    public ReceiptMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public DTOreceipt toReceiptDTO(Receipt receipt){
        return mapper.map(receipt, DTOreceipt.class);
    }

    public Receipt toReceipt(DTOreceipt dTOreceipt){
        return mapper.map(dTOreceipt, Receipt.class);
    }
}
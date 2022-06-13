package com.sofka.demo.mappers;

import com.sofka.demo.collections.Bill;
import com.sofka.demo.dtos.DTObill;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class BillMapper {

    private final ModelMapper mapper;

    public BillMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public DTObill toBillDTO(Bill bill){
        return mapper.map(bill, DTObill.class);
    }

    public Bill toBill(DTObill billDTO){
        return mapper.map(billDTO, Bill.class);
    }


}

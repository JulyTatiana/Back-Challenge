package com.sofka.demo.useCases;

import com.sofka.demo.dtos.DTObill;
import com.sofka.demo.mappers.BillMapper;
import com.sofka.demo.repositories.IntBillRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetBillUseCase {

    private final IntBillRepository repository;
    private final BillMapper mapper;


    public GetBillUseCase(IntBillRepository repository, BillMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Flux<DTObill> getAllProducts(){
        return repository.findAll().map(bill -> mapper.toBillDTO(bill));
    }
}

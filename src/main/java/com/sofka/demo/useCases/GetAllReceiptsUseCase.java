package com.sofka.demo.useCases;

import com.sofka.demo.dtos.DTOreceipt;
import com.sofka.demo.mappers.ReceiptMapper;
import com.sofka.demo.repositories.IntReceiptRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetAllReceiptsUseCase {

    private final IntReceiptRepository repository;
    private final ReceiptMapper mapper;


    public GetAllReceiptsUseCase(IntReceiptRepository repository, ReceiptMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Flux<DTOreceipt> getAllReceipts(){
        return repository.findAll().map(receipt -> mapper.toReceiptDTO(receipt));
    }
}

package com.sofka.demo.useCases;

import com.sofka.demo.dtos.DTOreceipt;
import com.sofka.demo.mappers.ReceiptMapper;
import com.sofka.demo.repositories.IntReceiptRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class CreateReceiptUseCase {

    private final IntReceiptRepository repository;
    private final ReceiptMapper mapper;

    public CreateReceiptUseCase(IntReceiptRepository repository, ReceiptMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    private boolean validateAttribute(DTOreceipt dTOreceipt){
        return dTOreceipt.getProviderId() != null &&
                dTOreceipt.getProductId() != null &&
                dTOreceipt.getProductUnits() != null;
    }

    private Mono<DTOreceipt> validateReceipt(DTOreceipt dTOreceipt){
        return  Mono.just(dTOreceipt)
                .filter(receiptDTO1 -> this.validateAttribute(receiptDTO1))
                .switchIfEmpty(Mono.error(() -> new Exception("Missing Attributes")));
    }

    public Mono<DTOreceipt> createReceipt(DTOreceipt dTOreceipt){
        dTOreceipt.setDate(LocalDateTime.now(ZoneId.of("America/Bogota")).toString());
        return validateReceipt(dTOreceipt)
                .flatMap(receiptDTO1 -> repository.save(mapper.toReceipt(receiptDTO1)))
                .map(receipt -> mapper.toReceiptDTO(receipt));
    }
}
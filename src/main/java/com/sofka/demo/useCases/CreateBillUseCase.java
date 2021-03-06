package com.sofka.demo.useCases;

import com.sofka.demo.dtos.DTObill;
import com.sofka.demo.mappers.BillMapper;
import com.sofka.demo.repositories.IntBillRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class CreateBillUseCase {

    private final IntBillRepository repository;
    private final BillMapper mapper;

    public CreateBillUseCase(IntBillRepository repository, BillMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    private boolean validateAttribute(DTObill dTObill){
        return dTObill.getClient() != null &&
                dTObill.getSeller() != null &&
                dTObill.getTotalPaid() != null &&
                dTObill.getProductId() != null;
    }

    private Mono<DTObill> validateBill(DTObill dTObill){
        return  Mono.just(dTObill)
                .filter(billDTO1 -> this.validateAttribute(billDTO1))
                .switchIfEmpty(Mono.error(() -> new Exception("Missing Attributes")));
    }

    public Mono<DTObill> createBill(DTObill dTObill){
        dTObill.setDate(LocalDateTime.now(ZoneId.of("America/Bogota")).toString());
        return validateBill(dTObill)
                .flatMap(billDTO1 -> repository.save(mapper.toBill(billDTO1)))
                .map(bill -> mapper.toBillDTO(bill));
    }
}
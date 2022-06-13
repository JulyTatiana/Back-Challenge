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

    private boolean validateAttributes(DTObill dTObill){
        return dTObill.getDate() != null &&
                dTObill.getClient() != null &&
                dTObill.getSeller() != null &&
                dTObill.getTotalPaid() != null &&
                dTObill.getProductId() != null;
    }

    private Mono<DTObill> validateBill(DTObill dTObill){
        return Mono.just(dTObill)
                .filter(billDTO1 -> this.validateAttributes(billDTO1))
                .switchIfEmpty(Mono.error(()-> new Exception("There are missing attributes")));
    }

    public Mono<DTObill> createBill(DTObill dtObill){
        dtObill.setDate(LocalDateTime.now(ZoneId.of("America/Bogota")).toString());
        return validateBill(dtObill)
                .flatMap(billDTO1 -> repository.save(mapper.toBill(billDTO1)))
                .map(bill -> mapper.toBillDTO(bill));
    }
}
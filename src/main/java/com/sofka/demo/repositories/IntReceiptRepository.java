package com.sofka.demo.repositories;

import com.sofka.demo.collections.Receipt;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntReceiptRepository extends ReactiveMongoRepository<Receipt, String> {
}

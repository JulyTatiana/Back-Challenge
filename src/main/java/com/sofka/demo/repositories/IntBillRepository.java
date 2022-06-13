package com.sofka.demo.repositories;

import com.sofka.demo.collections.Bill;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntBillRepository extends ReactiveMongoRepository<Bill, String> {
}
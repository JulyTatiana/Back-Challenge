package com.sofka.demo.repositories;

import com.sofka.demo.collections.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntProductRepository extends ReactiveMongoRepository<Product, String> {

}

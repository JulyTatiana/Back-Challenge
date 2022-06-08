package com.sofka.demo.repository;


import com.sofka.demo.collections.Provider;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProviderRepository extends ReactiveMongoRepository<Provider, String> {
    
}

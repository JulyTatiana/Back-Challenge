package com.sofka.demo.repositories;


import com.sofka.demo.collections.Provider;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IntProviderRepository extends ReactiveMongoRepository<Provider, String> {

    Mono<Provider> findByproviderName(String providerName);
}

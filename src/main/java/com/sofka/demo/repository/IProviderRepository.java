package com.sofka.demo.repository;


import com.sofka.demo.DTO.ProviderDTO;
import com.sofka.demo.collections.Provider;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IProviderRepository extends ReactiveMongoRepository<Provider, String> {

    Mono<Provider> findByproviderName(String providerName);
}

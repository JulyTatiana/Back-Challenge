package com.sofka.demo.routes;

import com.sofka.demo.dtos.DTOreceipt;
import com.sofka.demo.useCases.CreateReceiptUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CreateReceiptRoute {

    @Bean
    public RouterFunction<ServerResponse> createReceipt(CreateReceiptUseCase createReceiptUseCase){
        return route(POST("/create/receipt").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(DTOreceipt.class)
                        .flatMap(receiptDTO -> createReceiptUseCase.createReceipt(receiptDTO))
                        .flatMap(receiptDTO -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(receiptDTO))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }
}
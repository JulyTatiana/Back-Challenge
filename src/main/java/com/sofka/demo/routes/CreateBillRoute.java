package com.sofka.demo.routes;

import com.sofka.demo.dtos.DTObill;
import com.sofka.demo.useCases.CreateBillUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CreateBillRoute {

    @Bean
    public RouterFunction<ServerResponse> createOneBill(CreateBillUseCase createBillUseCase){
        return route(POST("/create/bill").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(DTObill.class)
                        .flatMap(billDTO -> createBillUseCase.createBill(billDTO))
                        .flatMap(billDTO -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(billDTO))
                        .onErrorResume(throwable -> ServerResponse.badRequest().build()));
    }
}


package com.sofka.demo.routes;

import com.sofka.demo.dtos.DTOproduct;
import com.sofka.demo.useCases.CreateProductUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CreateProductRoute {

    @Bean
    @RouterOperation(operation = @Operation(description = "Create bill ", operationId = "createBill", tags = "Bills",
            responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BillsDTO.class)))))
    public RouterFunction<ServerResponse> newProduct(CreateProductUseCase createProductUseCase){
        return route(POST("/create/product").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(DTOproduct.class)
                        .flatMap(DTOproduct -> createProductUseCase.createProduct(DTOproduct))
                        .flatMap(DTOproduct -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(DTOproduct))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }
}

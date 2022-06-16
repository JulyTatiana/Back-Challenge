package com.sofka.demo.routes;

import com.sofka.demo.dtos.DTObill;
import com.sofka.demo.useCases.CreateBillUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class CreateBillRoute {

    @Bean
    @RouterOperation(operation = @Operation(description = "Create bill ", operationId = "createBill", tags = "Bills", responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DTObill.class)))))
    public RouterFunction<ServerResponse> createBill(CreateBillUseCase createBillUseCase){
        return route(POST("/create/bill").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(DTObill.class)
                        .flatMap(billDTO -> createBillUseCase.createBill(billDTO))
                        .flatMap(billDTO -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(billDTO))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }
}


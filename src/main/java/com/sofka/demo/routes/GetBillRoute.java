package com.sofka.demo.routes;

import com.sofka.demo.dtos.DTObill;
import com.sofka.demo.useCases.GetBillUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetBillRoute {

    @Bean
    @RouterOperation(operation = @Operation(description = "Get all bills ", operationId = "getBill", tags = "Bills", responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DTObill.class)))))
    public RouterFunction<ServerResponse> getOneBill(GetBillUseCase getBillUseCase){
        return route(GET("/get/bills"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(getBillUseCase.getAllProducts(), DTObill.class)));
    }
}

package com.sofka.demo.routes;

import com.sofka.demo.dtos.DTOproduct;
import com.sofka.demo.dtos.DTOprovider;
import com.sofka.demo.useCases.CreateProviderUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CreateProviderRoute {

    @Bean
    @RouterOperation(operation = @Operation(description = "Create Provider ", operationId = "createProvider", tags = "Providers",
            responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DTOprovider.class)))))
    public RouterFunction<ServerResponse> create(CreateProviderUseCase createProviderUseCase){
        return route(POST("/create/provider").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(DTOprovider.class)
                        .flatMap(providerDTO -> createProviderUseCase.createProvider(providerDTO))
                        .flatMap(providerDTO -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(providerDTO))
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.BAD_REQUEST).build()));
    }
}

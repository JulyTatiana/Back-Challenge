package com.sofka.demo.routes;

import com.sofka.demo.dtos.DTObill;
import com.sofka.demo.dtos.DTOprovider;
import com.sofka.demo.useCases.DeleteProviderUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteProviderRoute {

    @Bean
    @RouterOperation(operation = @Operation(description = "Delete Provider ", operationId = "deleteProvider", tags = "Providers", responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DTOprovider.class)))))
    public RouterFunction<ServerResponse> deleteProvider(DeleteProviderUseCase deleteProviderUseCase){
        return route(DELETE("/delete/provider/{id}"),
                request -> deleteProviderUseCase.deleteProviderById(request.pathVariable("id"))
                        .flatMap((unused) -> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
    }
}

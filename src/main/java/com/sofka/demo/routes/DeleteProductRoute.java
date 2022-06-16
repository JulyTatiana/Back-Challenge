package com.sofka.demo.routes;

import com.sofka.demo.dtos.DTObill;
import com.sofka.demo.dtos.DTOproduct;
import com.sofka.demo.useCases.DeleteProductUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteProductRoute {

    @Bean
    @RouterOperation(operation = @Operation(description = "Delete product ", operationId = "deleteProduct", tags = "Products", responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DTOproduct.class)))))
    public RouterFunction<ServerResponse> delete(DeleteProductUseCase deleteProductUseCase){
        return route(DELETE("/delete/product/{id}"),
                request -> deleteProductUseCase.deleteProduct(request.pathVariable("id"))
                        .flatMap((unused) -> ServerResponse.accepted().build())
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }
}

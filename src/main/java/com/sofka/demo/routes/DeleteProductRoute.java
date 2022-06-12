package com.sofka.demo.routes;

import com.sofka.demo.useCases.DeleteProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteProductRoute {

    @Bean
    public RouterFunction<ServerResponse> delete(DeleteProductUseCase deleteProductUseCase){
        return route(DELETE("/delete/product/{id}"),
                request -> deleteProductUseCase.deleteProduct(request.pathVariable("id"))
                        .flatMap((unused) -> ServerResponse.accepted().build())
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }
}

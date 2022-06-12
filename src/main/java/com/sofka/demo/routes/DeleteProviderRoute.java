package com.sofka.demo.routes;

import com.sofka.demo.useCases.DeleteProviderUseCase;
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
    public RouterFunction<ServerResponse> deleteProvider(DeleteProviderUseCase deleteProviderUseCase){
        return route(DELETE("/delete/provider/{id}"),
                request -> deleteProviderUseCase.deleteProviderById(request.pathVariable("id"))
                        .flatMap((unused) -> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
    }
}

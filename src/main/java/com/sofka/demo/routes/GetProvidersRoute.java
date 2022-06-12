package com.sofka.demo.routes;

import com.sofka.demo.dtos.DTOprovider;
import com.sofka.demo.useCases.GetAllProvidersUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class GetProvidersRoute {

    @Bean
    //Bean control inversion instead "of instantiating ourselves", it does it for ourselves

    public RouterFunction<ServerResponse> allProviders(GetAllProvidersUseCase getAllProvidersUseCase){
        return route(GET("/get/providers"), request -> ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(getAllProvidersUseCase.getAllProviders(), DTOprovider.class)));
    }
}

package com.sofka.demo.routes;

import com.sofka.demo.usecases.GetProvidersUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class GetProvidersRoute {

    @Bean
    //Bean control inversion instead of instantiating myself it does it for ourselves

    public RouterFunction<ServerResponse> allProviders(GetProvidersUseCase){

    }

}

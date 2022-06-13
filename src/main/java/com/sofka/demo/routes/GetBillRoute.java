package com.sofka.demo.routes;

import com.sofka.demo.dtos.DTObill;
import com.sofka.demo.useCases.GetBillUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
http://localhost:8090/delete/product/62a6c1127f0160727381c7f9
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetBillRoute {

    @Bean
    public RouterFunction<ServerResponse> getOneBill(GetBillUseCase getBillUseCase){
        return route(GET("/get/bills"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(getBillUseCase.getAllProducts(), DTObill.class)));
    }
}

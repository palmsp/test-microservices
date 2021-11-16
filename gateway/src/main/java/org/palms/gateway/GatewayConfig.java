package org.palms.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class GatewayConfig {

   // @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("first", r -> r.path("/first/**")
                        .uri("lb://my-load-balanced-service"))

                .route("second", r -> r.path("/second/**")
                        .uri("http://localhost:8082/"))
                .build();
    }

}

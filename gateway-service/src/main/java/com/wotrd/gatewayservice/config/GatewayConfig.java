package com.wotrd.gatewayservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
public class GatewayConfig {

    @Value("${test.uri}")
    private String uri;


    @Bean
    public RouteLocator configRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/jianshu")
                        .uri("http://www.jianshu.com/u/128b6effde53")
                ).build();
    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                //basic proxy
//                .route(r -> r.path("/order/**")
//                        .uri(uri)
//                )
//                .route(r -> r.path("/user/**")
//                        .filters(f -> f
//                                .hystrix(config -> config
//                                        .setName("myserviceOne")
//                                        .setFallbackUri("forward:/user/fallback")))
//                        .uri(uri)).build();
//    }
//

}

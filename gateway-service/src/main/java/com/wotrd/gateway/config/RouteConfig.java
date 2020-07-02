package com.wotrd.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * @ClassName: RouteConfig
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/6/29 23:20
 */
//@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        Collection<GatewayFilter> fils = null;
        return builder.routes()
                .route("path_route", predicateSpec ->
                        predicateSpec.path("/feign-service")
                                .uri("http://www.ailijie.top")
                                .filter(new GlobalGateFilter())
                )
                .build();
    }
}

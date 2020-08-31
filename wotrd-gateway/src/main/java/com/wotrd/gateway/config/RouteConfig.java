package com.wotrd.gateway.config;

import com.wotrd.gateway.handler.HystrixFallbackHandler;
import com.wotrd.gateway.service.ImageCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * @ClassName: RouteConfig
 * @Description: 路由配置
 * @Author: wotrd
 * @Date: 2020/08/31 23:20
 */
@Configuration
public class RouteConfig {

    @Autowired
    private ImageCodeService codeService;

    @Autowired
    private HystrixFallbackHandler hystrixFallbackHandler;

//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        Collection<GatewayFilter> fils = null;
//        return builder.routes()
//                .route("path_route", predicateSpec ->
//                        predicateSpec.path("/feign-service")
//                                .uri("http://www.ailijie.top")
////                                .filter(new GlobalGateFilter())
//                ).build();
//    }

    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions.route(
                RequestPredicates.path("/fallback")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), hystrixFallbackHandler)
                .andRoute(RequestPredicates.GET("/code")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), codeService);

    }
}

package com.wotrd.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @ClassName: GateFilter
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/6/29 09:43
 */
@Component
public class GateFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Map<String, String> uriVariables = ServerWebExchangeUtils.getUriTemplateVariables(exchange);

        String segment = uriVariables.get("segment");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}

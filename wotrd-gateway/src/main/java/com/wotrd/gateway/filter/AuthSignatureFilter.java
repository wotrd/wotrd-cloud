package com.wotrd.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 调用鉴权
 */
@Slf4j
@Component
public class AuthSignatureFilter implements GlobalFilter, Ordered {

    // 排除过滤的 uri 地址
    private static final String[] WHITE_LIST = {"/*/v2/api-docs", "/user/register", "/swagger-ui.html",
            "/swagger-resources/**",
            "/*/api-docs",
            "/api/socket/**",
            "/log"};

    private AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String uriPath = request.getPath().toString();

        log.info("url:{}", uriPath);
        boolean action = false;
        for (String url : WHITE_LIST) {
            if (antPathMatcher.match(url, uriPath)) {
                action = true;
                break;
            }
        }
        // 跳过不需要验证的路径
        if (action) {
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (null == token || token.isEmpty()) {
            ServerHttpResponse response = exchange.getResponse();
            //当请求不携带Token或者token为空时，直接设置请求状态码为401，返回
            InetSocketAddress remoteAddress = request.getRemoteAddress();
            String clientIp = Objects.requireNonNull(remoteAddress).getAddress().getHostAddress();
            log.info("非法请求，客户端IP：" + clientIp + "URL：" + request.getPath());
            JSONObject message = new JSONObject();
            message.put("code", HttpStatus.UNAUTHORIZED.value());
            message.put("msg", "非法请求");
            byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //指定编码，否则在浏览器中会中文乱码
            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));

        }
        ServerHttpRequest authorization = request.mutate().headers(httpHeaders -> {
            httpHeaders.add("Authorization", token);
        }).build();
        ServerWebExchange serverWebExchange = exchange.mutate().request(authorization).build();
        return chain.filter(serverWebExchange);
    }

    @Override
    public int getOrder() {
        return -200;
    }
}

package com.wotrd.gateway.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName: GateFilter
 * @Description: 全局过滤器
 * @Author: wotrd
 * @Date: 2020/6/29 09:43
 */
@Component
public class GlobalGateFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();
        // 从请求中获取 token 参数
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        // 如果为空，那么将返回 401
        if (token == null || token.isEmpty()) {
            // 响应消息内容对象
            JSONObject message = new JSONObject();
            // 响应状态
            message.put("code", -1);
            // 响应内容
            message.put("msg", "缺少凭证");
            // 转换响应消息内容对象为字节
            byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            // 设置响应对象状态码 401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 设置响应对象内容并且指定编码，否则在浏览器中会中文乱码
            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            // 返回响应对象
            return response.writeWith(Mono.just(buffer));
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}

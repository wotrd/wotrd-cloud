package com.wotrd.gateway.filter;

import com.wotrd.gateway.constant.GatewayConstant;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

/**
 * @Classname ImageCodeFilter
 * @Description 图形验证码验证
 * @Author wotrd
 * @Date 2020-08-31 10:34
 * @Version 1.0
 */
@Slf4j
@Component
public class ImageCodeFilter extends AbstractGatewayFilterFactory {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            // 不是登录请求，直接向下执行
            String path = request.getURI().getPath();
            if (!path.toLowerCase().contains(GatewayConstant.OAUTH_TOKEN_URL)) {
                return chain.filter(exchange);
            }
            // 验证流程
            validateCode(request);
            return chain.filter(exchange);
        };
    }

    /**
     * 验证流程
     *
     * @param request
     */
    @SneakyThrows
    private void validateCode(ServerHttpRequest request) {

        MultiValueMap<String, String> queryParams = request.getQueryParams();
        // 验证码
        String code = queryParams.getFirst("code");
        // 随机标识
        String t = queryParams.getFirst("t");
        // 验证验证码流程
        if (StringUtils.isEmpty(code)) {
            throw new RuntimeException("验证码不能为空");
        }
        // 从redis中获取之前保存的验证码跟前台传来的验证码进行匹配
        String kaptCha = redisTemplate.opsForValue().get(GatewayConstant.WOTRD_IMAGE_CODE_KEY + t);
        if (kaptCha == null) {
            throw new RuntimeException("验证码已失效");
        }
        if (!code.toLowerCase().equals(kaptCha)) {
            throw new RuntimeException("验证码错误");
        }
    }
}

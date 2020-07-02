package com.wotrd.gateway.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: RedisRouteDeifinationRepository
 * @Description: 动态路由配置
 * @Author: wotrd
 * @Date: 2020/7/1 12:59
 */
@Repository
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    private static final String GATEWAY_ROUTES = "GATEWAY_ROUTES";

    @Autowired
    private StringRedisTemplate template;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        String json = "{\"filters\":[{\"args\":{\"_genkey_0\":\"header\",\"_genkey_1\":\"addHeader\"},\"name\":\"AddRequestHeader\"},{\"args\":{\"_genkey_0\":\"param\",\"_genkey_1\":\"addParam\"},\"name\":\"AddRequestParameter\"}],\"id\":\"id\",\"metadata\":{},\"order\":0,\"predicates\":[{\"args\":{\"pattern\":\"/jd\"},\"name\":\"Path\"}],\"uri\":\"http://127.0.0.1:8888/header\"}";
        RouteDefinition routeDefinition = JSONObject.parseObject(json, RouteDefinition.class);
        List<RouteDefinition> routeDefinitions = Lists.newArrayList(routeDefinition);

//        template.opsForHash().values(GATEWAY_ROUTES).stream()
//                .forEach(routeDefinition -> routeDefinitions.add(JSONObject.parseObject(routeDefinition.toString(), RouteDefinition.class)));

        return Flux.fromIterable(routeDefinitions);

    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}

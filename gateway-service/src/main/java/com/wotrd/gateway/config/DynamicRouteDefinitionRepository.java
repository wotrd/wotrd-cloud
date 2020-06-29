package com.wotrd.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DynamicRouteDefinitionRepository
 * @Description: 动态网关配置
 * @Author: wotrd
 * @Date: 2020/6/27 19:51
 */
@RefreshScope
@Slf4j
@Component
public class DynamicRouteDefinitionRepository implements RouteDefinitionRepository {

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        log.info("gateway网关加载路由配置开始");
        // todo 从数据库获取网关数据
        List<RouteDefinition> definations = new ArrayList<>();
        log.info("gateway网关加载路由配置完成");
        return Flux.fromIterable(definations);
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

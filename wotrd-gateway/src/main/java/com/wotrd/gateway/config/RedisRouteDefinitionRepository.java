package com.wotrd.gateway.config;

import com.alibaba.fastjson.JSONObject;
import com.wotrd.gateway.model.*;
import com.wotrd.gateway.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: RedisRouteDeifinationRepository
 * @Description: 动态路由配置
 * @Author: wotrd
 * @Date: 2020/7/1 12:59
 */
@Slf4j
@Repository
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    @Autowired
    private RouteService routeService;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        log.info("[gateway] change gate start");
        List<RouteDefinition> definitions = getDefinitions();
        log.info("[gateway] change gate success:{}", JSONObject.toJSON(definitions));
        return Flux.fromIterable(definitions);

    }

    /**
     * 获取网关配置
     *
     * @return
     */
    private List<RouteDefinition> getDefinitions() {

        List<Route> routes = routeService.routeList();
        List<RouteDefinition> routeDefinitions = new ArrayList<>(routes.size());
        routes.stream().forEach(route -> {
            RouteDefinition routeDefinition = new RouteDefinition();
            routeDefinition.setId(route.getId());
            routeDefinition.setOrder(route.getOrder());
            routeDefinition.setMetadata(getMetadata(route.getMetadatas()));
            List<FilterDefinition> filters = getFilters(route.getFilters());
            routeDefinition.setFilters(filters);
            List<PredicateDefinition> predicates = getPredicates(route.getPredicates());
            routeDefinition.setPredicates(predicates);
            routeDefinition.setUri(getUri(route.getTargetUrl()));
            routeDefinitions.add(routeDefinition);
        });

        return routeDefinitions;
    }

    /**
     * 获取断言列表
     *
     * @param predicates
     * @return
     */
    private List<PredicateDefinition> getPredicates(List<RoutePredicate> predicates) {
        List<PredicateDefinition> definitions = new ArrayList<>(predicates.size());
        predicates.stream().forEach(routePredicate -> {
            PredicateDefinition definition = new PredicateDefinition();
            definition.setArgs(getPredicateArg(routePredicate.getPredicateArgs()));
            definition.setName(routePredicate.getPredicateName());
            definitions.add(definition);
        });

        return definitions;
    }

    /**
     * 获取断言参数
     *
     * @param predicateArgs
     * @return
     */
    private Map<String, String> getPredicateArg(List<RoutePredicateArgs> predicateArgs) {
        Map<String, String> map = new HashMap<>();
        predicateArgs.stream().forEach(routePredicateArg -> {
            map.put(routePredicateArg.getKey(), routePredicateArg.getValue());
        });
        return map;
    }

    /**
     * 获取过滤器
     *
     * @param filters
     * @return
     */
    private List<FilterDefinition> getFilters(List<RouteFilter> filters) {
        List<FilterDefinition> definitions = new ArrayList<>(filters.size());
        filters.stream().forEach(routeFilter -> {
            FilterDefinition definition = new FilterDefinition();
            definition.setName(routeFilter.getFilterName());
            definition.setArgs(getFilterArgs(routeFilter.getFilterArgs()));
            definitions.add(definition);
        });
        return definitions;
    }

    /**
     * 获取过滤器参数
     *
     * @param filterArgs
     * @return
     */
    private Map<String, String> getFilterArgs(List<RouteFilterArgs> filterArgs) {
        Map<String, String> map = new HashMap<>();
        filterArgs.stream().forEach(routeFilterArg -> {
            map.put(routeFilterArg.getKey(), routeFilterArg.getValue());
        });
        return map;
    }

    /**
     * 获取地址uri
     *
     * @param url
     * @return
     */
    private URI getUri(String url) {
        if (url.startsWith("http") || url.startsWith("https")){
            return UriComponentsBuilder.fromHttpUrl(url).build().toUri();
        }
        return UriComponentsBuilder.fromPath(url).build().toUri();
    }

    /**
     * 获取元数据map
     *
     * @param metadatas
     * @return
     */
    private Map<String, Object> getMetadata(List<RouteMetadata> metadatas) {
        Map<String, Object> map = new HashMap<>();
        metadatas.stream().forEach(routeMetadata -> {
            map.put(routeMetadata.getKey(), routeMetadata.getValue());
        });
        return map;
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

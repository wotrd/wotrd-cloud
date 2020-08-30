package com.wotrd.gateway.service;

import com.alibaba.fastjson.JSONObject;
import com.wotrd.gateway.model.*;
import com.wotrd.gateway.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName: RouteService
 * @Description: 路由服务
 * @Author: wotrd
 * @Date: 2020/7/1 15:44
 */
@Slf4j
@Service
public class RouteService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RouteMapper routeMapper;

    @Autowired
    private RouteMetadataMapper routeMetadataMapper;

    @Autowired
    private RouteFilterMapper routeFilterMapper;

    @Autowired
    private RouteFilterArgsMapper routeFilterArgsMapper;

    @Autowired
    private RoutePredicateMapper predicateMapper;

    @Autowired
    private RoutePredicateArgsMapper routePredicateArgsMapper;

    @Autowired
    private StringRedisTemplate template;

    private static final String GATEWAY_ROUTES = "GATEWAY_ROUTES";

    /**
     * 刷新路由
     */
    public void refresh() {
        publisher.publishEvent(new RefreshRoutesEvent(this));

    }

    /**
     * 添加路由
     *
     * @param route
     */
    @Transactional(rollbackFor = Exception.class)
    public void addRoute(Route route) {
        //插入路由
        routeMapper.insert(route);
        //插入过滤器
        List<RouteFilter> filters = route.getFilters();
        if (!CollectionUtils.isEmpty(filters)) {
            filters.forEach(routeFilter -> {
                routeFilterMapper.insert(routeFilter);
                List<RouteFilterArgs> filterArgs = routeFilter.getFilterArgs();
                filterArgs.forEach(routeFilterArgs -> {
                    routeFilterArgsMapper.insert(routeFilterArgs);
                });
            });
        }
        //插入元数据
        List<RouteMetadata> metadatas = route.getMetadatas();
        if (!CollectionUtils.isEmpty(metadatas)) {
            metadatas.forEach(routeMetadata -> {
                routeMetadataMapper.insert(routeMetadata);
            });
        }
        //插入断言
        List<RoutePredicate> predicates = route.getPredicates();
        if (!CollectionUtils.isEmpty(predicates)) {
            predicates.forEach(routePredicate -> {
                predicateMapper.insert(routePredicate);
                List<RoutePredicateArgs> predicateArgs = routePredicate.getPredicateArgs();
                predicateArgs.forEach(routePredicateArgs -> {
                    routePredicateArgsMapper.insert(routePredicateArgs);
                });
            });
        }
        template.opsForValue().set(GATEWAY_ROUTES, JSONObject.toJSONString(routeList()));
        refresh();
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    public void deleteRoute(String id) {
        routeMapper.deleteById(id);
        template.opsForValue().set(GATEWAY_ROUTES, JSONObject.toJSONString(routeList()));
        refresh();
    }

    /**
     * 删除路由
     */
    public void deleteRoute() {
        routeMapper.delete();
        template.delete(GATEWAY_ROUTES);
        refresh();
    }

    /**
     * 获取路由列表
     *
     * @return
     */
    public List<Route> routeList() {
        try {
            String routeStr = template.opsForValue().get(GATEWAY_ROUTES);
            if (!StringUtils.isEmpty(routeStr)) {
                return JSONObject.parseArray(routeStr, Route.class);
            }
        } catch (Exception e) {
            log.error("[gateway] get route from redis error:", e);
        }
        List<Route> routes = routeMapper.selectAll();
        if (!CollectionUtils.isEmpty(routes)) {
            routes.stream().forEach(route -> {
                route.setMetadatas(getRouteMetadatas(route.getId()));
                route.setFilters(getRouteFilters(route.getId()));
                route.setPredicates(getRoutePredicates(route.getId()));
            });
        }
        try {
            template.opsForValue().set(GATEWAY_ROUTES, JSONObject.toJSONString(routes));
        } catch (Exception e) {
            log.error("[gateway] set route to redis error:", e);
        }
        return routes;
    }

    /**
     * 查询断言列表
     *
     * @param routeId
     * @return
     */
    private List<RoutePredicate> getRoutePredicates(String routeId) {
        List<RoutePredicate> predicateList = predicateMapper.selectByRouteId(routeId);
        predicateList.stream().forEach(routePredicate -> {
            routePredicate.setPredicateArgs(getRoutePredicateArgs(routePredicate.getId()));
        });
        return predicateList;

    }

    /**
     * 查询断言参数列表
     *
     * @param predicateId
     * @return
     */
    private List<RoutePredicateArgs> getRoutePredicateArgs(Long predicateId) {
        return routePredicateArgsMapper.selectByPredicateId(predicateId);
    }

    /**
     * 查询过滤器数据
     *
     * @param routeId
     * @return
     */
    private List<RouteFilter> getRouteFilters(String routeId) {
        List<RouteFilter> fileters = routeFilterMapper.selectByRouteId(routeId);
        if (!CollectionUtils.isEmpty(fileters)) {
            fileters.stream().forEach(routeFilter -> {
                routeFilter.setFilterArgs(getRouteFilterArgs(routeFilter.getId()));
            });
        }
        return fileters;

    }

    /**
     * 查询过滤器参数集合
     *
     * @param filterId
     * @return
     */
    private List<RouteFilterArgs> getRouteFilterArgs(Long filterId) {
        return routeFilterArgsMapper.selectByFilterId(filterId);

    }

    /**
     * 查询源数据
     *
     * @param routeId
     * @return
     */
    private List<RouteMetadata> getRouteMetadatas(String routeId) {
        return routeMetadataMapper.selectByRouteId(routeId);

    }


}

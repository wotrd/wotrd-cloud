package com.wotrd.gateway.mapper;

import com.wotrd.gateway.domain.RoutePredicateArgs;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutePredicateArgsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoutePredicateArgs record);

    int insertSelective(RoutePredicateArgs record);

    RoutePredicateArgs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoutePredicateArgs record);

    int updateByPrimaryKey(RoutePredicateArgs record);
}
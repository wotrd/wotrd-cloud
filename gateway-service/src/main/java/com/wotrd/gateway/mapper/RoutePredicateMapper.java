package com.wotrd.gateway.mapper;

import com.wotrd.gateway.domain.RoutePredicate;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutePredicateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoutePredicate record);

    int insertSelective(RoutePredicate record);

    RoutePredicate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoutePredicate record);

    int updateByPrimaryKey(RoutePredicate record);
}
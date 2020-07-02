package com.wotrd.gateway.mapper;

import com.wotrd.gateway.domain.RouteFilterArgs;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteFilterArgsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RouteFilterArgs record);

    int insertSelective(RouteFilterArgs record);

    RouteFilterArgs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RouteFilterArgs record);

    int updateByPrimaryKey(RouteFilterArgs record);
}
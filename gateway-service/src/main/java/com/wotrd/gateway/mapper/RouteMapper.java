package com.wotrd.gateway.mapper;

import com.wotrd.gateway.domain.Route;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteMapper {
    int deleteByPrimaryKey(String id);

    int insert(Route record);

    int insertSelective(Route record);

    Route selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Route record);

    int updateByPrimaryKey(Route record);
}
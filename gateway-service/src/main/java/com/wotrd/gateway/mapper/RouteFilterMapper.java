package com.wotrd.gateway.mapper;

import com.wotrd.gateway.domain.RouteFilter;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteFilterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RouteFilter record);

    int insertSelective(RouteFilter record);

    RouteFilter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RouteFilter record);

    int updateByPrimaryKey(RouteFilter record);
}
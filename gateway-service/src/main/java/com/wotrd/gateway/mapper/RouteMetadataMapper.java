package com.wotrd.gateway.mapper;

import com.wotrd.gateway.domain.RouteMetadata;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteMetadataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RouteMetadata record);

    int insertSelective(RouteMetadata record);

    RouteMetadata selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RouteMetadata record);

    int updateByPrimaryKey(RouteMetadata record);
}
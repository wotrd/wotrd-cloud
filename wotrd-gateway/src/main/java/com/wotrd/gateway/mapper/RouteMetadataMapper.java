package com.wotrd.gateway.mapper;

import com.wotrd.gateway.model.RouteMetadata;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RouteMetadataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RouteMetadata record);

    int insertSelective(RouteMetadata record);

    RouteMetadata selectByPrimaryKey(Long id);

    /**
     * 根据路由ID查询元数据列表
     *
     * @param routeId
     * @return
     */
    List<RouteMetadata> selectByRouteId(@Param("routeId") String routeId);

    int updateByPrimaryKey(RouteMetadata record);

}
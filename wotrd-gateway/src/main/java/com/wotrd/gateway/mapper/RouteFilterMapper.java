package com.wotrd.gateway.mapper;

import com.wotrd.gateway.model.RouteFilter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RouteFilterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RouteFilter record);

    int insertSelective(RouteFilter record);

    RouteFilter selectByPrimaryKey(Long id);

    /**
     * 根据路由ID查询
     *
     * @param routeId
     * @return
     */
    List<RouteFilter> selectByRouteId(@Param("routeId") String routeId);

    int updateByPrimaryKey(RouteFilter record);


}
package com.wotrd.gateway.mapper;

import com.wotrd.gateway.model.RoutePredicate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoutePredicateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoutePredicate record);

    int insertSelective(RoutePredicate record);

    RoutePredicate selectByPrimaryKey(Long id);

    /**
     * 根据路由ID查询
     *
     * @param routeId
     * @return
     */
    List<RoutePredicate> selectByRouteId(@Param("routeId") String routeId);

    int updateByPrimaryKey(RoutePredicate record);

}
package com.wotrd.gateway.mapper;

import com.wotrd.gateway.domain.Route;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RouteMapper {
    int deleteByPrimaryKey(String id);

    int insert(Route record);

    int insertSelective(Route record);

    Route selectByPrimaryKey(String id);

    /**
     * 查询全部路由
     *
     * @return
     */
    List<Route> selectAll();

    int updateByPrimaryKey(Route record);


}
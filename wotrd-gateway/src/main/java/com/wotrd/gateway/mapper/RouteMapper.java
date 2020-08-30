package com.wotrd.gateway.mapper;

import com.wotrd.gateway.model.Route;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RouteMapper {

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteById(@Param("id") String id);

    int insert(Route route);

    Route selectByPrimaryKey(String id);

    /**
     * 查询全部路由
     *
     * @return
     */
    List<Route> selectAll();

    /**
     * 删除全部路由
     *
     * @return
     */
    int delete();

}
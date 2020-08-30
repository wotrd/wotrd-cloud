package com.wotrd.gateway.mapper;

import com.wotrd.gateway.model.RouteFilterArgs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RouteFilterArgsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RouteFilterArgs record);

    int insertSelective(RouteFilterArgs record);

    RouteFilterArgs selectByPrimaryKey(Long id);

    /**
     * 根据过滤器ID查询
     *
     * @param filterId
     * @return
     */
    List<RouteFilterArgs> selectByFilterId(@Param("filterId") Long filterId);

    int updateByPrimaryKey(RouteFilterArgs record);

}
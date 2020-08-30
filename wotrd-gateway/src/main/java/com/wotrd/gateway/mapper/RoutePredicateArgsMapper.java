package com.wotrd.gateway.mapper;

import com.wotrd.gateway.model.RoutePredicateArgs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoutePredicateArgsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoutePredicateArgs record);

    /**
     * 根据断言ID查询
     *
     * @param predicateId
     * @return
     */
    List<RoutePredicateArgs> selectByPredicateId(@Param("predicateId") Long predicateId);

    RoutePredicateArgs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoutePredicateArgs record);

    int updateByPrimaryKey(RoutePredicateArgs record);

}
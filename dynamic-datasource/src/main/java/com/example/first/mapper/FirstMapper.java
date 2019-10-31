package com.example.first.mapper;

import com.example.first.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wotrd
 */
@Mapper
@Repository
public interface FirstMapper {

    Order getOrder(Long id);

    int update(@Param("count") Long count, Long id);


}

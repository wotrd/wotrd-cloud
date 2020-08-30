package com.wotrd.dydatasource.second.mapper;

import com.wotrd.dydatasource.second.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author wangkaijin
 */
@Mapper
@Repository
public interface SecondMapper {

    Order getOrder(Long id);

    int update(Long count, Long id);
}

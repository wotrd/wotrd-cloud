package com.example.first.mapper;

import com.example.first.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FirstMapper {

    Order getOrder();

}

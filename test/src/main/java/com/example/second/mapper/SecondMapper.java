package com.example.second.mapper;

import com.example.second.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SecondMapper {

    Order getOrder();

}

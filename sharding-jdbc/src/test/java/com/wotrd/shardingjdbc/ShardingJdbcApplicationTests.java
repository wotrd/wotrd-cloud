package com.wotrd.shardingjdbc;

import com.wotrd.shardingjdbc.domain.Order;
import com.wotrd.shardingjdbc.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbcApplicationTests {


    @Autowired
    private OrderMapper orderMapper;


    @Test
    public void insert() {
        Order order = Order.builder()
                .orderId(2).userId(1).build();

        orderMapper.insert(order);

        Long id = order.getId();
        log.info("Generated Key--id:" + id);
//        orderMapper.deleteById(id);
    }

    @Test
    public void findAll() {
        List<Order> orders = orderMapper.queryAll();
        log.info("user:{}", orders);
    }


}

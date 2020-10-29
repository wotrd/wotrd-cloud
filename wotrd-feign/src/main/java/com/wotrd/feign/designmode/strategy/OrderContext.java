package com.wotrd.feign.designmode.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName: OrderContext
 * @Description: 策略模式上下文
 * @Author: wotrd
 * @Date: 2020/10/29 17:46
 */
@Component
public class OrderContext {

    @Autowired
    private Map<String, OrderService> map;

    /**
     * 获取订单服务
     *
     * @param key
     * @return
     */
    public OrderService getOrderService(String key) {
        return map.get(key);
    }

}

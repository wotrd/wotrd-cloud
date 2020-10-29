package com.wotrd.feign.designmode.strategy;

import com.wotrd.feign.designmode.strategy.enums.OrderTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: OrderContextFactory
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/10/29 17:57
 */
public class OrderContextFactory {

    @Autowired
    private OrderContext orderContext;

    /**
     * 构建订单上下文
     *
     * @param serviceType 服务类型
     * @return
     */
    public OrderService buildOrderService(Integer serviceType) {
        return orderContext.getOrderService(OrderTypeEnum.getServiceName(serviceType).getServiceName());
    }

}

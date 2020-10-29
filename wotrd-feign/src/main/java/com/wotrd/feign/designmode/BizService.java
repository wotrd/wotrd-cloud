package com.wotrd.feign.designmode;

import com.wotrd.feign.designmode.enums.OrderTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: BizService
 * @Description: 业务服务
 * @Author: wotrd
 * @Date: 2020/10/29 17:49
 */
@Service
public class BizService {

    @Autowired
    private OrderContext orderContext;

    public void makeOrder(Integer type) {
        OrderTypeEnum orderTypeEnum = OrderTypeEnum.getServiceName(type);
        String serviceName = orderTypeEnum.getServiceName();
        OrderService orderService = orderContext.getOrderService(serviceName);
        orderService.makeOrder();

    }
}

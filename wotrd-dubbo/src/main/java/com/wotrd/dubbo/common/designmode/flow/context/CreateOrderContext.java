package com.wotrd.dubbo.common.designmode.flow.context;

import com.wotrd.dubbo.common.designmode.flow.domain.CreateOrderRequest;
import com.wotrd.dubbo.common.designmode.flow.domain.CreateOrderResult;
import lombok.Data;

/**
 * @description: 下单上下文
 * @Author: wotrd
 * @date: 2021/6/5 20:40
 */
@Data
public class CreateOrderContext {

    private CreateOrderRequest request;

    private CreateOrderResult result;

}

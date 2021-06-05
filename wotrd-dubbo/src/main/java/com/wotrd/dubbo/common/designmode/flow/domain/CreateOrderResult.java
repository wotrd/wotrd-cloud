package com.wotrd.dubbo.common.designmode.flow.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/6/5 20:42
 */
@Data
public class CreateOrderResult {

    private Long orderId;

    private String name;

    private Date gmtCreate;

    private BigDecimal totalPrice;
}

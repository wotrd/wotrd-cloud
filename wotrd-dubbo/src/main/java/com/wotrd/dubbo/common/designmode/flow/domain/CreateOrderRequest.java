package com.wotrd.dubbo.common.designmode.flow.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/6/5 20:41
 */
@Data
public class CreateOrderRequest {

    private Long id;

    private String name;

    private BigDecimal price;

}

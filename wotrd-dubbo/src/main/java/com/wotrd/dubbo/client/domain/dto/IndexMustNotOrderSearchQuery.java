package com.wotrd.dubbo.client.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/6/3 22:09
 */
@Data
public class IndexMustNotOrderSearchQuery {

    /**
     * 除订单ID的集合
     */
    private List<Long> notOrderIdList;
    /**
     * 不匹配的订单来源
     */
    private List<String> notOrderSourceList;

}
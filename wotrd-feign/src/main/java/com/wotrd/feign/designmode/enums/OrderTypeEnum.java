package com.wotrd.feign.designmode.enums;

import lombok.Getter;

/**
 * @ClassName: OrderTypeEnum
 * @Description: 类型和服务注册名字对应枚举
 * @Author: wotrd
 * @Date: 2020/10/29 18:01
 */
@Getter
public enum OrderTypeEnum {

    JD_SERVICE(1, "jdOrderServiceImpl"),
    TM_SERVICE(2, "tmOrderServiceImpl");

    private String serviceName;
    private Integer type;

    OrderTypeEnum(Integer type, String serviceName) {
        this.type = type;
        this.serviceName = serviceName;
    }

    /**
     * 根据类型获取服务名字
     *
     * @param type
     * @return
     */
    public static OrderTypeEnum getServiceName(Integer type) {
        for (OrderTypeEnum orderTypeEnum : values()) {
            if (orderTypeEnum.type == type) {
                return orderTypeEnum;
            }
        }
        return null;
    }

}

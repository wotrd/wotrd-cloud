package com.wotrd.dubbo.client.domain.dto;

import com.frameworkset.orm.annotation.ESId;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/5/19 00:33
 */
@Data
public class Demo {
    @ESId
    private Long demoId;

    private Date agentStartTime;

    private Date agentStartTimeZh;

    private String applicationName;

    private String contentBody;

    private String name;

    private String orderId;

    private Integer contrastStatus;

}

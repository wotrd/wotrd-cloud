package com.wotrd.gateway.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * route
 * @author 
 */
@Data
public class Route implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 应用ID
     */
    private Integer appId;

    /**
     * 应用名字
     */
    private String appName;

    /**
     * 目标地址
     */
    private String targetUrl;

    /**
     * 执行顺序
     */
    private Integer order;

    /**
     * 创建时间
     */
    private String createUser;

    private String updateUser;

    private Date createTime;

    private Date updateTime;

    /**
     * 是否删除（1是0否）
     */
    private Integer deleted;

    private static final long serialVersionUID = 1L;
}
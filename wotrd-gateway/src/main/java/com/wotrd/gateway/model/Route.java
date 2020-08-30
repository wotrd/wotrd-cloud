package com.wotrd.gateway.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * route
 * @author 
 */
@Data
public class Route implements Serializable {

    private static final long serialVersionUID = 1L;

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

    /**
     * 元数据列表
     */
    List<RouteMetadata> metadatas;

    /**
     * 过滤器列表
     */
    List<RouteFilter> filters;

    /**
     * 断言列表
     */
    List<RoutePredicate> predicates;
}
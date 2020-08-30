package com.wotrd.gateway.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * route_filter
 * @author 
 */
@Data
public class RouteFilter implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    private String routeId;

    /**
     * 过滤器名字
     */
    private String filterName;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 过滤器参数集合
     */
    private List<RouteFilterArgs> filterArgs;

}
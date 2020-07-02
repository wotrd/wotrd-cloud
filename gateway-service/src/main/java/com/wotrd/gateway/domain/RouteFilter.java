package com.wotrd.gateway.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * route_filter
 * @author 
 */
@Data
public class RouteFilter implements Serializable {
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

    private static final long serialVersionUID = 1L;
}
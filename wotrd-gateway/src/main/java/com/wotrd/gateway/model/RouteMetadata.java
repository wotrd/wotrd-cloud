package com.wotrd.gateway.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * route_metadata
 * @author 
 */
@Data
public class RouteMetadata implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    private String routeId;

    /**
     * 元数据key
     */
    private String key;

    /**
     * 元数据值
     */
    private String value;

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
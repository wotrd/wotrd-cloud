package com.wotrd.gateway.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * route_predicate_args
 * @author 
 */
@Data
public class RoutePredicateArgs implements Serializable {
    private Long id;

    private Long predicateId;

    private String key;

    private String value;

    private String updateUser;

    private String createUser;

    private Date updateTime;

    private Date createTime;

    private Integer deleted;

    private static final long serialVersionUID = 1L;
}
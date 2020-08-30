package com.wotrd.gateway.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * route_predicate
 * @author 
 */
@Data
public class RoutePredicate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String routeId;

    /**
     * 断言名字
     */
    private String predicateName;

    private Date updateTime;

    private String createUser;

    private String updateUser;

    private Date createTime;

    private Integer deleted;

    /**
     * 断言参数列表
     */
    private List<RoutePredicateArgs> predicateArgs;

}
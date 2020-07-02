package com.wotrd.gateway.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * route_predicate
 * @author 
 */
@Data
public class RoutePredicate implements Serializable {
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

    private static final long serialVersionUID = 1L;
}
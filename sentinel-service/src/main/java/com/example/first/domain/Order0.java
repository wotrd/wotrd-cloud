package com.example.first.domain;

import java.io.Serializable;

/**
 * (Order0)实体类
 *
 * @author wotrd
 * @since 2019-10-23 17:49:36
 */
public class Order0 implements Serializable {
    private static final long serialVersionUID = 403409304757314083L;
    
    private Long id;
    
    private Long userId;
    
    private Long orderId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

}
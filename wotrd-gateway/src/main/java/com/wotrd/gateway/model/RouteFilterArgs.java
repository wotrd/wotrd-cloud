package com.wotrd.gateway.model;

import java.io.Serializable;
import lombok.Data;

/**
 * route_filter_args
 * @author 
 */
@Data
public class RouteFilterArgs implements Serializable {
    private Long id;

    private Long filterId;

    private String key;

    private String value;

    private Integer deleted;

    private static final long serialVersionUID = 1L;
}
package com.wotrd.sharding.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * config
 * @author 
 */
@Data
public class Config implements Serializable {
    private Long id;

    private String name;

    private Integer type;

    private Integer deleted;

    private static final long serialVersionUID = 1L;
}
package com.wotrd.feign.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDTO {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private String authorName;

}

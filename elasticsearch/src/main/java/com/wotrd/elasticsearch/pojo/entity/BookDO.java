package com.wotrd.elasticsearch.pojo.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Data
@Document(indexName = "book")
public class BookDO {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private String authorName;

}

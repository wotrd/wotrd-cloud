package com.wotrd.gateway.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Data
@Document(indexName = "order_index", shards = 1, replicas = 0)
public class OrderDO {

    @Id
    private Long id;

    private String name;

    private Integer type;

    private BigDecimal price;

    private Integer deleted;


}

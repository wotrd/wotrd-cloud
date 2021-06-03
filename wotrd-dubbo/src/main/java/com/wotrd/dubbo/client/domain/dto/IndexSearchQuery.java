package com.wotrd.dubbo.client.domain.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/6/3 22:01
 */
@Data
public class IndexSearchQuery {

    /**
     * 子订单id
     */
    private Long orderId;
    /**
     * 子订单id的集合
     */
    private List<Long> orderIdList;
    /**
     * 开始时间
     */
    private Date gmtStart;
    /**
     * 结束时间
     */
    private Date gmtEnd;

    private IndexShouldSearchQuery indexShouldSearchQuery;
    /**
     * es中should查询 即是or查询
     */
    private IndexMustNotOrderSearchQuery indexMustNotOrderSearchQuery;


    /**
     * scrollid
     */
    private String scrollId;

    /**
     * 索引名
     */
    private String indexName;

    /**
     * 模板名
     */
    private String templateName;

    /**
     * 排序字段
     */
    private String sortColumn;

    /**
     * 排序
     */
    private String sort;

    /**
     * composite agg 操作 size
     */
    private Integer compositeSize;

    /**
     * composite agg 操作 afterKey
     */
    private String mainOrderIdAfterKey;

    /**
     * 折叠返回的子信息数量
     */
    private Integer collapseSize;

    /**
     * cardinality统计数量的精确值数, 最大值上限是40000个
     */
    private Integer precisionThresholdSize;

    /**
     * 去重查询的列名 eg:orderId(根据主订单id去重)
     */
    private String cardinalityParam;

}
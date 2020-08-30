package com.wotrd.feign.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * books
 * @author 
 */
@Data
public class Books implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 图书名字
     */
    private String name;

    /**
     * 图书类型
     */
    private String type;

    /**
     * 图书单价
     */
    private BigDecimal price;

    /**
     * tushu图片
     */
    private String avatar;

    /**
     * 买家ID
     */
    private Integer sellerId;

    /**
     * 卖家名字
     */
    private String sellerName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}
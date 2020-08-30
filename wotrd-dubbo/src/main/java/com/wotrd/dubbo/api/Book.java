package com.wotrd.dubbo.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @ClassName: User
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/6/20 20:41
 */
@Data
@ApiModel(description = "图书实体类")
public class Book implements Serializable {

    @ApiModelProperty(value = "图书主键")
    private Long id;

    @ApiModelProperty(value = "图书名字", required = true, allowEmptyValue = true)
    private String name;

    @ApiModelProperty(value = "图书价格", required = true, example = "1.0")
    private BigDecimal price;

    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}

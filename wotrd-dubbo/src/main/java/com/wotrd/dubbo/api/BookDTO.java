package com.wotrd.dubbo.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: BookDTO
 * @Description: BookDTO
 * @Author: wotrd
 * @Date: 2020/6/20 20:41
 */
@Data
@ApiModel(description = "图书传输类")
public class BookDTO implements Serializable {

    @ApiModelProperty(value = "图书主键")
    private Long id;

    @ApiModelProperty(value = "图书列表", allowEmptyValue = true)
    private List<Book> books;


}

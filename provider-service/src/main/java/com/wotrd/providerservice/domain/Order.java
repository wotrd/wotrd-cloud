package com.wotrd.providerservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * (Order)实体类
 *
 * @author wotrd
 * @since 2019-05-26 00:12:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {
    private static final long serialVersionUID = -29427066560562595L;
    //主键ID
    private Long id;
    //订单名字
    @NotEmpty(message = "name订单名字格式错误")
    private String name;
    //订单价格
    @NotNull(message = "price订单价格格式错误")
    private BigDecimal price;

}
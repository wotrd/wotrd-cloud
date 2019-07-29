package com.wotrd.shardingjdbc.domain;

import lombok.*;

import java.io.Serializable;

/**
 * (Order0)实体类
 *
 * @author wotrd
 * @since 2019-07-28 23:39:02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Order implements Serializable {
    private static final long serialVersionUID = 427226138907372838L;
    private Long id;

    private Integer userId;
    private Integer orderId;

}
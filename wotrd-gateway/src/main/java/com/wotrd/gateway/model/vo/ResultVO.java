package com.wotrd.gateway.model.vo;

import lombok.Data;

/**
 * @ClassName: ResultVO
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/8/31 20:02
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T body;

}

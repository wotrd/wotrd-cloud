package com.wotrd.caffeine.domain;

import lombok.Data;

import java.util.Date;

/**
 * @className CacheDO
 * @description TODO
 * @Author wotrd
 * @date 2020/12/15 16:21
 */
@Data
public class CacheDO {

    private Long id;

    private String name;

    private Date gmtCreate;
}

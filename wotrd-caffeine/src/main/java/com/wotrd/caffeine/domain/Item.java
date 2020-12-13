package com.wotrd.caffeine.domain;

import lombok.Data;

import java.util.Date;

/**
 * @className Item
 * @description TODO
 * @Author wotrd
 * @date 2020/12/13 4:15 下午
 */
@Data
public class Item {

    private Long id;

    private String name;

    private Date gmtCreate;

    private String createUser;

}

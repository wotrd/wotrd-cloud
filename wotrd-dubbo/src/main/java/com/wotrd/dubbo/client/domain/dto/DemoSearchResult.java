package com.wotrd.dubbo.client.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/5/19 00:49
 */
@Data
public class DemoSearchResult {

    private List<Demo> demos;

    private Long totalSize;
}

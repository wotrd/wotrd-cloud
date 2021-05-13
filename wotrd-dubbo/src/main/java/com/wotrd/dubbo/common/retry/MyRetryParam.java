package com.wotrd.dubbo.common.retry;

import com.wotrd.dubbo.common.retry.base.BaseTaskParam;
import lombok.Data;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/5/13 18:15
 */
@Data
public class MyRetryParam implements BaseTaskParam {

    private String key;

}

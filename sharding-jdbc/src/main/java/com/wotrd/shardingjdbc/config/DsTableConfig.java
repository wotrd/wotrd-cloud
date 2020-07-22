package com.wotrd.shardingjdbc.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @ClassName: DsTableConfig
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/7/22 23:57
 */
@Component
public class DsTableConfig implements PreciseShardingAlgorithm {
    @Override
    public String doSharding(Collection collection, PreciseShardingValue preciseShardingValue) {
//        collection.contains();
        return null;
    }
}

package com.wotrd.sharding.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @ClassName: ShardingDsConfig
 * @Description: 自定义分库算法
 * @Author: wotrd
 * @Date: 2020/7/22 23:57
 */
@Component
public class ShardingDsConfig implements PreciseShardingAlgorithm<Integer> {

    private static final String DB_NAME = "ds";

    @Override
    public String doSharding(Collection<String> databases, PreciseShardingValue<Integer> preciseShardingValue) {
        Integer value = preciseShardingValue.getValue();
        for (String ds : databases) {
            Integer suffix = value % 2;
            if (ds.equals(DB_NAME + suffix)) {
                return DB_NAME + suffix;
            }
        }
        return DB_NAME + "0";

    }

}

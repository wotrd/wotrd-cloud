package com.wotrd.sharding.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @ClassName: DsTableConfig
 * @Description: 自定义分表规则
 * @Author: wotrd
 * @Date: 2020/7/22 23:57
 */
@Component
public class ShardingTableConfig implements PreciseShardingAlgorithm<Integer> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        String tableName = preciseShardingValue.getLogicTableName();
        Integer value = preciseShardingValue.getValue();
        Integer suffix = value % 2;
        for (String tb : collection) {
            if (tb.equals(tableName + suffix)) {
                return tb;
            }
        }

        return tableName + "0";
    }
}

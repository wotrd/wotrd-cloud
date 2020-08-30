package com.wotrd.dubbo.common.enums;

import com.wotrd.dubbo.common.enums.strategy.HighStrategy;
import com.wotrd.dubbo.common.enums.strategy.LowStrategy;
import com.wotrd.dubbo.common.enums.strategy.StantardStrategy;
import lombok.Getter;

/**
 * @ClassName: StrategyEnum
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/8/29 19:59
 */
@Getter
public enum StrategyEnum {

    LOW_STRATEGY {
        public Strategy execute(int code) {
            return new LowStrategy();
        }
    },
    STANDARD_STRATEGY {
        public Strategy execute(int code) {
            return new StantardStrategy();
        }
    },
    HIGH_STRATEGY {
        public Strategy execute(int code) {
            return new HighStrategy();
        }
    };

    public abstract Strategy execute(int code);
}

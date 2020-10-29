package com.wotrd.feign.designmode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName: JdOrderServiceImpl
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/10/29 17:44
 */
@Slf4j
@Service("jdOrderServiceImpl")
public class JdOrderServiceImpl implements OrderService {
    @Override
    public void makeOrder() {
        log.info("jd make order");
    }
}

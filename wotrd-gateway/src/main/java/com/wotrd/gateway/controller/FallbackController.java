package com.wotrd.gateway.controller;

import com.wotrd.gateway.constant.GatewayConstant;
import com.wotrd.gateway.model.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname FallbackController
 * @Description TODO
 * @Author wotrd
 * @Date 2020-08-31 16:06
 */
@RestController
public class FallbackController {

    @RequestMapping("fallback")
    public ResultVO fallback() {
        ResultVO resultVO = new ResultVO();
        resultVO.setMsg(GatewayConstant.SERVER_ERROR);
        resultVO.setCode(500);
        return resultVO;
    }
}


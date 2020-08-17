package com.wotrd.dubboprovider.pay.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.example.springbootwebdemo.pay.alipay.config.AlipayConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Api(value = "aliPayController", tags = {"阿里支付调用类"})
@CrossOrigin
@RestController
@RequestMapping(value = "/alipay")
public class aliPayController {

    private static final Logger logger = Logger.getLogger(aliPayController.class.getName());

    /**
     * 支付宝支付成功后.回调该接口
     *
     * @param request
     * @throws IOException
     * @returnAlipayClient
     */
    @ApiOperation(value = "支付宝支付成功后.回调该接口")
    @RequestMapping(value = "notify", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String notify(HttpServletRequest request) {
        logger.info("--------支付宝异步返回支付结果开始--------");
        Map<String, String> params = new HashMap<>();
        // 1.从支付宝回调的request域中取值
        Map<String, String[]> requestParams = request.getParameterMap();
        logger.info("支付宝支付结果通知：");
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            StringBuilder valueBuff = new StringBuilder();
            for (String value : values) {
                valueBuff.append(value).append(",");
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueBuff.substring(0, valueBuff.length() - 1));
        }
        // 2.封装必须参数
        // 商户订单号,orderNumber
        String outTradeNo = params.get("out_trade_no");
        // 该交易在支付宝系统中的交易流水号。最长64位。
        String tradeNo = params.get("trade_no");
        // 买家支付宝账号
        String buyerLogonId = params.get("buyer_logon_id");
        // 订单金额
        String totalAmount = params.get("total_amount");
        // 实收金额
        String receiptAmount = params.get("receipt_amount");
        // 付款金额
        String buyerPayAmount = params.get("buyer_pay_amount");
        // 交易付款时间
        String gmtPayment = params.get("gmt_payment");
        // 交易状态
        String tradeStatus = params.get("trade_status");
        // ticketId
        String outRequestNo = params.get("out_request_no");
        // 退票时间，根据退票金额判断是支付回调还是退票回调
        String refundFee = params.get("refund_fee");

        // 3.签名验证(对支付宝返回的数据验证，确定是支付宝返回的)
        boolean signVerified = false;
        try {
            // 3.1调用SDK验证签名
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET,
                    AlipayConfig.SIGNTYPE);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        // 4.对验签进行处理
        if (signVerified) {
            // 验签通过
            //添加一个日志操作,先将map转成bean
            if (null == refundFee) {
                //支付回调
                if ("TRADE_SUCCESS".equals(tradeStatus)) {
                    // 支付成功
                } else {
                    //支付失败 ，修改交易表状态,支付失败
                    return "fail";
                }
                return "success";
            } else {
                //退款回调
                return "success";
            }
        } else {
            // 验签不通过
            logger.info("验签失败");
            return "fail";
        }
    }
}

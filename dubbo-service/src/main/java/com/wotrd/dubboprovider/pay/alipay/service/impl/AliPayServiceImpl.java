package com.wotrd.dubboprovider.pay.alipay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.example.springbootwebdemo.pay.alipay.config.AlipayConfig;
import com.example.springbootwebdemo.pay.alipay.service.AliPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AliPayServiceImpl implements AliPayService {


    private AlipayClient alipayClient;

    private final static Logger logger = LoggerFactory.getLogger(AliPayServiceImpl.class);

    @Autowired
    public AliPayServiceImpl() {
        this.alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
                AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
    }

    @Override
    public Map payOrder(BigDecimal tradeMoney, String orderNumber, String timeoutExpress) {
        Map<String, String> result = new HashMap();
        try {
            Map<String, String> orderMap = new LinkedHashMap<>(); // 订单实体
            // 手机端用
            // 商户订单号，商户网站订单系统中唯一订单号，必填
            orderMap.put("out_trade_no", orderNumber);
            // 订单名称，必填
            orderMap.put("subject", "行在岱山——船票服务");
            // 付款金额，必填
            orderMap.put("total_amount", tradeMoney.toString());
            // 商品描述，可空
            orderMap.put("body", "您购买船票" + tradeMoney.toString() + "元");
            // 超时时间 可空
            orderMap.put("timeout_express", timeoutExpress + "m");
            // 销售产品码 必填
            orderMap.put("product_code", "QUICK_MSECURITY_PAY");

            // 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest ali_request = new AlipayTradeAppPayRequest();

            // SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            model.setPassbackParams(URLEncoder.encode(orderMap.get("body"), "UTF-8"));// 描述信息
            // 添加附加数据
            model.setBody(orderMap.get("body")); // 商品信息
            model.setSubject(orderMap.get("subject")); // 商品名称
            model.setOutTradeNo(orderMap.get("out_trade_no")); // 商户订单号 orderNumber
            model.setTimeoutExpress(orderMap.get("timeout_express")); // 交易超时时间
            model.setTotalAmount(orderMap.get("total_amount")); // 支付金额
            model.setProductCode(orderMap.get("product_code")); // 销售产品码
            ali_request.setBizModel(model);
            ali_request.setNotifyUrl(AlipayConfig.notify_url); // 回调地址

            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(ali_request);
            String resultStr = response.getBody();
            logger.info(result.toString()); // 就是orderString 可以直接给客户端请求，无需再做处理。

            result.put("code", "200");
            result.put("msg", resultStr);
            return result;
        } catch (Exception e) {
            result.put("code", "500");
            result.put("msg", e.getMessage());
            return result;
        }
    }

    /**
     * 支付宝退款接口
     * ticketId 作为 out_request_no 传入
     * @param refundMoney
     * @param orderNumber
     * @param ticketId
     * @return
     */
    @Override
    public Map refundOrder(String refundMoney, String orderNumber, String ticketId) {
        Map<String, String> result = new HashMap<>();
        try {
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            AlipayTradeRefundModel model = new AlipayTradeRefundModel();
            model.setOutTradeNo(orderNumber);
            model.setRefundAmount(refundMoney);
            model.setOutRequestNo(ticketId);
            model.setRefundReason("船票退款");
            request.setBizModel(model);
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            logger.info("response=" + response);
            if (response.isSuccess()) {
                result.put("code", "200");
                result.put("msg", "退款发起成功");
                return result;
            } else {
                result.put("code", "500");
                result.put("msg", response.getSubMsg());
                return result;
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            result.put("code", "500");
            result.put("msg", e.getMessage());
            return result;
        }
    }

    /**
     * 支付宝退款查询接口
     * ticketId 作为 out_request_on 传入
     *
     * @param orderNumber
     * @param ticketId
     * @return
     */
    @Override
    public Map refundStatus(String orderNumber, String ticketId) {
        Map<String, String> result = new HashMap<>();
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
        model.setOutTradeNo(orderNumber);
        model.setOutRequestNo(ticketId);
        request.setBizModel(model);
        try {
            AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);

            if (response.isSuccess()) {
                if (StringUtils.isEmpty(response.getRefundAmount())) {
                    result.put("code", "201");
                    result.put("msg", "退款未到账");
                } else {
                    result.put("code", "200");
                    result.put("msg", "退款已到账，本次退款金额中买家退款金额" + response.getPresentRefundBuyerAmount());
                }
                return result;
            } else {
                result.put("code", "500");
                result.put("msg", response.getSubMsg());
                return result;
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            result.put("code", "500");
            result.put("msg", e.getMessage());
            return result;
        }
    }

}

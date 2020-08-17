package com.wotrd.dubboprovider.pay.wechatpay.service.impl;

import com.example.springbootwebdemo.pay.wechatpay.config.PayCommonUtil;
import com.example.springbootwebdemo.pay.wechatpay.config.WechatConfig;
import com.example.springbootwebdemo.pay.wechatpay.service.WechatPayService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.example.springbootwebdemo.pay.wechatpay.config.MD5Util.createSign;
import static com.example.springbootwebdemo.pay.wechatpay.config.XMLUtil.SortedMaptoXml;

@Service
public class WechatPayServiceImpl implements WechatPayService {

    private static final Logger logger = LogManager.getLogger(WechatPayServiceImpl.class);
    private String randomString = PayCommonUtil.getRandomString(32);

    private static final String url = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    @Override
    public SortedMap<Object, Object> wechatPay(BigDecimal totalAmount, String orderNumber, String timeoutExpress, String ip) {
        String trade_no = "";
        String description = "";
        try {
            trade_no = new String(orderNumber.getBytes("ISO-8859-1"), "UTF-8");
            description = WechatConfig.BODY;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, String> map;
        SortedMap<Object, Object> result = new TreeMap<>();

        try {
            map = weixinPrePay(trade_no, totalAmount, description, ip);
            if (null == map) {
                logger.error("微信付款调起失败");
                result.put("code", "401");
                result.put("msg", "微信付款调起失败");
                return result;
            }
        } catch (Exception e) {
            logger.error("微信付款调起失败" + e.getMessage());
            result.put("code", "400");
            result.put("msg", "微信付款调起失败" + e.getMessage());
            return result;
        }

        Long time = (System.currentTimeMillis() / 1000);

        SortedMap<String, String> parameterMap = new TreeMap<>();
        parameterMap.put("appid", WechatConfig.APP_ID);
        parameterMap.put("partnerid", WechatConfig.MCH_ID);
        parameterMap.put("prepayid", map.get("prepay_id"));
        parameterMap.put("package", "Sign=WXPay");
        parameterMap.put("noncestr", map.get("nonce_str"));
        //本来生成的时间戳是13位，但是ios必须是10位，所以截取了一下
        // parameterMap2.put("timestamp", String.valueOf(System.currentTimeMillis()).toString().substring(0,10));
        parameterMap.put("timestamp", time.toString());
        parameterMap.put("sign", map.get("sign"));

        String sign;
        try {
            sign = createSign(parameterMap, WechatConfig.APP_KEY).toUpperCase();
            if ("".equals(sign)) {
                logger.error("微信付款调起失败");
                result.put("code", "403");
                result.put("msg", "微信付款调起失败");
                return result;
            }
        } catch (Exception e) {
            logger.error("微信付款调起失败" + e.getMessage());
            result.put("code", "402");
            result.put("msg", "微信付款调起失败" + e.getMessage());
            return result;
        }

        result.put("appid", WechatConfig.APP_ID);
        result.put("partnerid", WechatConfig.MCH_ID);
        result.put("prepayid", map.get("prepay_id"));
        result.put("package", "Sign=WXPay");
        result.put("noncestr", map.get("nonce_str"));
        //本来生成的时间戳是13位，但是ios必须是10位，所以截取了一下
        // parameterMap2.put("timestamp", String.valueOf(System.currentTimeMillis()).toString().substring(0,10));
        result.put("timestamp", time.toString());
        result.put("sign", sign);
        result.put("code", "200");
        result.put("msg", "订单生成成功");

        return result;
    }

    @Override
    public Map wechatRefund(BigDecimal refundMoney, String orderNumber, String ticketId, BigDecimal totalMoney) {
        Map<String, String> result = new HashMap<>();
        //将refundMoney的单位变为分
        refundMoney = refundMoney.multiply(BigDecimal.valueOf(100));
        totalMoney = totalMoney.multiply(BigDecimal.valueOf(100));
        int refundFee = refundMoney.intValue();
        int totalFee = totalMoney.intValue();
        String param = wxPayRefundDo(orderNumber, ticketId, String.valueOf(refundFee), String.valueOf(totalFee));

        try {
            String resultXml = wxPayBack(url, param);
            logger.info("result" + resultXml);
            if (null != resultXml) {
                Map<String, String> resultParams = PayCommonUtil.doXMLParse(resultXml);
                if (resultParams.containsKey("return_code")) {
                    if ("SUCCESS".equals(resultParams.get("return_code"))) {
                        //退款发起成功
                        //outRequestNo就是ticketid
                        String outRequestNo = resultParams.get("out_refund_no");
                        String outTradeNo = resultParams.get("out_trade_no");
                        //处理逻辑
                        logger.error("微信退款发起成功");
                        result.put("code", "200");
                        result.put("msg", "success");
                        return result;
                    } else {
                        logger.error("微信退款发起失败");
                        result.put("code", "203");
                        result.put("msg", resultParams.get("err_code_des"));
                        return result;
                    }
                } else {
                    logger.error("微信退款发起失败");
                    result.put("code", "203");
                    result.put("msg", "微信退款发起失败");
                    return result;
                }
            } else {
                logger.error("微信退款发起失败");
                result.put("code", "201");
                result.put("msg", "微信退款发起失败");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("微信退款发起失败");
            result.put("code", "204");
            result.put("msg", e.getMessage());
            return result;
        }
    }

    @Override
    public Map wechatRefundStatus(String orderNumber, String ticketId) {
        SortedMap<String, Object> parameterMap = new TreeMap<>();
        Map<String, String> resultMap = new HashMap();
        //应用appid
        parameterMap.put("appid", WechatConfig.APP_ID);
        //商户号
        parameterMap.put("mch_id", WechatConfig.MCH_ID);
        parameterMap.put("nonce_str", randomString);
        parameterMap.put("out_refund_no", ticketId);
        String sign = PayCommonUtil.createSign("UTF-8", parameterMap);
        parameterMap.put("sign", sign);
        String requestXML = PayCommonUtil.getRequestXml(parameterMap);
        String result = PayCommonUtil.httpsRequest(
                "https://api.mch.weixin.qq.com/pay/refundquery", "POST",
                requestXML);
        if (null == result) {
            logger.error("微信退款状态发起失败");
            resultMap.put("code", "202");
            resultMap.put("msg", "微信返回值为空，请检查代码");
            return resultMap;
        } else {
            Map<String, String> map;
            try {
                map = PayCommonUtil.doXMLParse(result);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("微信退款状态返回值解析有误");
                resultMap.put("code", "202");
                resultMap.put("msg", "微信退款状态返回值解析有误，请检查代码");
                return resultMap;
            }

            //因为默认只返回一条数据，其他数据都不关注，如果有两条或者以上的数据，就需要注意代码的问题。
            if (!"1".equals(map.get("refund_count"))) {
                logger.error("微信退款查询返回值不唯一，请注意！");
            }
            if ("SUCCESS".equals(map.get("refund_status_0"))) {
                //退款成功
                resultMap.put("code", "200");
                resultMap.put("msg", "微信退款状态查询成功");
                if (map.containsKey("out_trade_no")) {
                    resultMap.put("orderNumber", map.get("out_trade_no"));
                }
                if (map.containsKey("out_refund_no_0")) {
                    resultMap.put("ticketId", map.get("out_refund_no_0"));
                }
                if (map.containsKey("refund_success_time_0")) {
                    resultMap.put("refundSuccessTime", map.get("refund_success_time_0"));
                }
                if (map.containsKey("refund_recv_accout_0")) {
                    resultMap.put("refundRecvAccount", map.get("refund_recv_accout_0"));
                }
                return resultMap;
            } else if ("REFUNDCLOSE".equals(map.get("refund_status_0"))) {
                //退款关闭
                resultMap.put("code", "203");
                resultMap.put("msg", "当前订单退款已关闭");
                return resultMap;
            } else if ("PROCESSING".equals(map.get("refund_status_0"))) {
                //退款处理中
                resultMap.put("code", "201");
                resultMap.put("msg", "当前订单退款正在处理中");
                return resultMap;
            } else {
                //退款异常
                resultMap.put("code", "500");
                resultMap.put("msg", "当前订单退款异常：可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。");
                return resultMap;
            }
        }
    }

    private static String wxPayRefundDo(String out_trade_no, String out_refund_no, String refund_fee, String total_fee) {
        String data;
        try {
            String nonceStr = PayCommonUtil.getRandomString(32);
            SortedMap<String, String> parameters = new TreeMap<>();
            parameters.put("appid", WechatConfig.APP_ID);
            parameters.put("mch_id", WechatConfig.MCH_ID);
            parameters.put("nonce_str", nonceStr);
            parameters.put("out_trade_no", out_trade_no);
            parameters.put("out_refund_no", out_refund_no);
            parameters.put("fee_type", "CNY");
            parameters.put("total_fee", total_fee);
            parameters.put("refund_fee", refund_fee);
            parameters.put("op_user_id", WechatConfig.MCH_ID);
            parameters.put("notify_url", WechatConfig.NOTIFY_REFUND_URL);
            parameters.put("sign", createSign(parameters, WechatConfig.APP_KEY));
            data = SortedMaptoXml(parameters);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
        return data;
    }

    private Map<String, String> weixinPrePay(String trade_no, BigDecimal totalAmount,
                                             String description, String ip) {
        SortedMap<String, Object> parameterMap = new TreeMap<>();
        parameterMap.put("appid", WechatConfig.APP_ID);
        parameterMap.put("mch_id", WechatConfig.MCH_ID);
        parameterMap.put("nonce_str", randomString);
        parameterMap.put("body", description);
        parameterMap.put("out_trade_no", trade_no);
        parameterMap.put("fee_type", "CNY");
        //接口中参数支付金额单位为【分】，参数值不能带小数，所以乘以100
        BigDecimal total = totalAmount.multiply(new BigDecimal(100));
        java.text.DecimalFormat df = new java.text.DecimalFormat("0");
        parameterMap.put("total_fee", df.format(total));
        if (StringUtils.isEmpty(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            parameterMap.put("spbill_create_ip", "127.0.0.1");
        } else {
            logger.info(ip);
            parameterMap.put("spbill_create_ip", ip);
        }
        parameterMap.put("notify_url", WechatConfig.NOTIFY_URL);
        parameterMap.put("trade_type", "APP");
        String sign = PayCommonUtil.createSign("UTF-8", parameterMap);
        logger.info(sign);
        parameterMap.put("sign", sign);
        String requestXML = PayCommonUtil.getRequestXml(parameterMap);
        logger.info(requestXML);
        String result = PayCommonUtil.httpsRequest(
                "https://api.mch.weixin.qq.com/pay/unifiedorder", "POST",
                requestXML);
        logger.info(result);
        Map<String, String> map = null;
        try {
            map = PayCommonUtil.doXMLParse(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 证书使用
     * 微信退款
     */
    private static String wxPayBack(String url, String data) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        //证书路径
        String path = "/linkcld/certificate/apiclient_cert.p12";
        FileInputStream instream = new FileInputStream(new File(path));
        String result = "";
        try {
            keyStore.load(instream, WechatConfig.MCH_ID.toCharArray());
        } finally {
            instream.close();
        }

        /* Trust own CA and all self-signed certs */
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, WechatConfig.MCH_ID.toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
            HttpPost httppost = new HttpPost(url);
            StringEntity entitys = new StringEntity(data);
            httppost.setEntity(entitys);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    StringBuilder t = new StringBuilder();
                    while ((text = bufferedReader.readLine()) != null) {
                        t.append(text);
                    }
                    byte[] temp = t.toString().getBytes("gbk");
                    result = new String(temp, "utf-8");
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return result;
    }

}

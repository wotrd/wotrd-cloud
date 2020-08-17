package com.wotrd.dubboprovider.pay.wechatpay.config;

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
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.example.springbootwebdemo.pay.wechatpay.config.MD5Util.createSign;
import static com.example.springbootwebdemo.pay.wechatpay.config.PayCommonUtil.getRandomString;
import static com.example.springbootwebdemo.pay.wechatpay.config.XMLUtil.SortedMaptoXml;

@Component
public class WechatUtil {

    private static final Logger logger = LoggerFactory.getLogger(WechatUtil.class);

    private static String randomString = getRandomString(32);

    /**
     * 将参数拼接成XML String
     *
     * @param out_trade_no
     * @param out_refund_no
     * @param refund_fee
     * @param total_fee
     * @return
     */
    public static String wxPayRefundDo(String out_trade_no, String out_refund_no, String refund_fee, String total_fee) {
        String data;
        try {
            String nonceStr = getRandomString(32);
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

    public static Map<String, String> weixinPrePay(String trade_no, BigDecimal totalAmount,
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
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 证书使用
     * 微信退款
     */
    public static String wxPayBack(String url, String data) throws Exception {
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

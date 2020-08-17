package com.wotrd.dubboprovider.pay.wechatpay.controller;

import com.example.springbootwebdemo.pay.wechatpay.config.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jdom.JDOMException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.Security;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static sun.security.x509.CertificateAlgorithmId.ALGORITHM;

@Api(value = "WechatPayController", tags = {"微信支付类"} )
@Controller
@RequestMapping("/wechatPay")
@CrossOrigin
public class WechatPayController {

    private static final Logger logger = Logger.getLogger(WechatPayController.class.getName());

    private static final String SUCCESS = "SUCCESS";

    private static SecretKeySpec key = new SecretKeySpec(MD5Util.MD5Encode(WechatConfig.APP_KEY, "UTF-8").toLowerCase().getBytes(), ALGORITHM);

    private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS7Padding";

    /**
     * 此函数会被执行多次，如果支付状态已经修改为已支付，则下次再调的时候判断是否已经支付，如果已经支付了，则什么也执行
     *
     * @param request
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "notifyWeiXinPay",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "notifyWeiXinPay", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String notifyWeiXinPay(HttpServletRequest request) throws IOException, JDOMException {
        logger.info("微信支付回调");
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        String resultxml = new String(outSteam.toByteArray(), "utf-8");
        Map<String, String> params = PayCommonUtil.doXMLParse(resultxml);
        outSteam.close();
        inStream.close();

        Map<String, String> returnData = new HashMap<>();
        if (params == null || !PayCommonUtil.isTenpaySign(params)) {
            // 支付失败
            returnData.put("return_code", "FAIL");
            returnData.put("return_msg", "return_code不正确");
            return StringUtil.GetMapToXML(returnData);
        } else {
            logger.info("===============付款成功==============");
            String total_fee = params.get("total_fee");
            BigDecimal bigDecimal = new BigDecimal(total_fee);
            bigDecimal = bigDecimal.divide(BigDecimal.valueOf(100));
            String outTradeNo = params.get("out_trade_no");
            String ordertime = DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
            String totalAmount = bigDecimal.toString();
            String openid = params.get("openid");
            String tradeNo = params.get("transaction_id");
            String cashFee = params.get("cash_fee");
            }
            returnData.put("return_code", "SUCCESS");
            returnData.put("return_msg", "OK");
            return StringUtil.GetMapToXML(returnData);

    }
    @ApiOperation(value = "notifyWeiXinRefund",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "notifyWeiXinRefund", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String notifyWeiXinRefund(HttpServletRequest request) throws Exception {
        logger.info("微信退款回调");
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        String resultxml = new String(outSteam.toByteArray(), "utf-8");
        Map<String, String> params = PayCommonUtil.doXMLParse(resultxml);
        outSteam.close();
        inStream.close();

        Map<String, String> returnData = new HashMap<>();
        if (null != params && params.containsKey("return_code")) {
            if (SUCCESS.equals(params.get("return_code"))) {
                //回调返回成功，开始解密获取返回值
                if (params.containsKey("req_info")) {
                    String reqInfo = params.get("req_info");
                    //解密
                    Security.addProvider(new BouncyCastleProvider());
                    String reqXml = decryptData(reqInfo);
                    //解析xml
                    Map<String, String> reqParams = PayCommonUtil.doXMLParse(reqXml);

                    String transactionId = reqParams.get("transaction_id");
                    String outTradeNo = reqParams.get("out_trade_no");
                    String refundId = reqParams.get("refund_id");
                    String outRefundNo = reqParams.get("out_refund_no");
                    String totalFee = reqParams.get("total_fee");
                    BigDecimal totalFeeBD = new BigDecimal(totalFee);
                    String settlementTotalFee = "";
                    if (reqParams.containsKey("settlement_total_fee")) {
                        settlementTotalFee = reqParams.get("settlement_total_fee");
                    }
                    String refundFee = reqParams.get("refund_fee");
                    BigDecimal refundFeeBD = new BigDecimal(totalFee);
                    String settlementRefundFee = reqParams.get("settlement_refund_fee");
                    BigDecimal settlementRefundFeeBD = new BigDecimal(settlementRefundFee);
                    String refundStatus = reqParams.get("refund_status");
                    if (reqParams.containsKey("success_time")) {
                        String successTime = reqParams.get("success_time");
                    }
                    String refundRecvAccout = reqParams.get("refund_recv_accout");
                    String refundRequestSource = reqParams.get("refund_request_source");

                    //根据refundStatus来判断退款是否成功，然后执行逻辑操作
                    logger.info("支付退票成功");
                    //修改乘客订单表信息
                    returnData.put("return_code", SUCCESS);
                    returnData.put("return_msg", "OK");
                    return StringUtil.GetMapToXML(returnData);
                } else {
                    // 退款回调失败
                    logger.info("订单退款回调失败,未获取到加密数据");
                    returnData.put("return_code", "FAIL");
                    returnData.put("return_msg", "订单退款回调失败,未获取到加密数据");
                    return StringUtil.GetMapToXML(returnData);
                }
            } else {
                // 退款回调失败
                logger.info("订单退款回调失败");
                returnData.put("return_code", "FAIL");
                returnData.put("return_msg", params.get("return_msg"));
                return StringUtil.GetMapToXML(returnData);
            }
        } else {
            // 退款回调失败
            logger.info("订单退款回调失败");
            returnData.put("return_code", "FAIL");
            returnData.put("return_msg", "return_code不正确");
            return StringUtil.GetMapToXML(returnData);
        }
    }

    private static byte[] decode(String key) throws Exception {
        return new BASE64Decoder().decodeBuffer(key);
    }

    private static String decryptData(String base64Data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(decode(base64Data)));
    }

}

package com.wotrd.dubboprovider.pay.wechatpay.config;

public class WechatConfig {

    /**
     * 微信开发平台应用ID
     */
    public static final String APP_ID = "wx86329aab2bb8f2f2";
    /**
     * 应用对应的凭证
     */
    public static final String APP_SECRET = "85ea6d0976184f36b680e15bdf72c107";
    /**
     * 应用对应的密钥
     */
    public static final String APP_KEY = "1nRXE6W36dPb9EmFHwi7iKIAseCzPGN5";
    /**
     * 微信支付商户号
     */
    public static final String MCH_ID = "1512128711";
    /**
     * 商品描述
     */
    public static final String BODY = "行在岱山——船票服务";
    /**
     * 商户号对应的密钥
     */
    public static final String PARTNER_key = "";

    /**
     * 商户id
     */
    public static final String PARTNER_ID = "";
    /**
     * 常量固定值
     */
    public static final String GRANT_TYPE = "client_credential";
    /**
     * 获取预支付id的接口url
     */
    public static String GATEURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 微信服务器回调通知url
     */
    public static String NOTIFY_URL = "https://pts.linkcld.com/pts-server-test/ticketService/wechatPay/notifyWeiXinPay";

    /**
     * 微信退款服务器回调通知url
     */
    public static String NOTIFY_REFUND_URL = "https://pts.linkcld.com/pts-server-test/ticketService/wechatPay/notifyWeiXinRefund";
}

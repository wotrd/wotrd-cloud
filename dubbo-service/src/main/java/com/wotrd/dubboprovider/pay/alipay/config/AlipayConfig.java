package com.wotrd.dubboprovider.pay.alipay.config;

public class AlipayConfig {

    // 1.商户appid
    public static String APPID = "2018082261107173";

    // 2.私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCNXz/d7AZgYilUo/QCqTBQxziuHg86qdR71mKhcV2bd0mxL8ahjwPFcnzdC7CGOrwQb7ZvVhBYsy4DQdiJnomZ9/kKQwwjWtLIJbwjs436ihVCtoNvPIy3QStGHMQ6t1Q2ZovU+bIiuCiQbL2rWAEEGO9ho5Q2BvJ8QCH70PwxZfYSc7L+qsEpXztbMTM6542Nn426WQSps8KHzG7Ju3pHyvgcE9/g2UEPsPqy3HVzM/qfpZeGKT8KPUUTklsH18gh4hGYeI//xgSqpqHOtYeC8Gu3FwH0NZLW46PF/ixgkc3ongeAmTk+xjVFcZRjKldAbplgEFbCf5fWPS7N83EhAgMBAAECggEARvNFv1/kN10sdwEtxmQZDoFdje0iIYP4UwpeR734h7zPbDCZFJ4M+3wg9GMYfdVeazVVrvzXiVyrvBDA1xiM4IwWHbNWlSvQ24gsqxJDMDk6wFePETWnPZVkXXS0eu9lQUQn64RBhbE9Enawnhy1rdPB2BPbn13PNtKM+69/0l4lGGKZRTGnJPjHQ7cgs+jAbP22QzLpIyFhCSaaOSdsnWRZZ5mUFBKeFCNVurhX33J0JV9YTyUyifgo31e2qpP32ObyVNj203OBimTpZx/zvUfL0DbKQcKwOKuDJWIChZ7AYC0JQ/LCNa2HAL1yYkbAKBtnZg4UwcpcBwL3a98ZEQKBgQDOCm36Alw4c7aEVSutF/3OcaAWIKK29gVXj88dK6kbA63yqKZ95EWDW2UyAYahlge8EoWRB6i2zylPnpGrth4FklolWesUsqfFKd7eptKBW3EkTp+l9KXJDl+d8D2/OOEu2E7Pic91tQxQhezKxCXwjEBjSvcTeOmEEqccd71XNQKBgQCvpqPpqfHVHP2TAaTyGxZbkimkkK6iZjXUg4H5ZVWZIODzlLNxTo2TU4H25NQzPfCarPJ26X60nhdqc5Liz6l5XCG5+0OM/0hhCMIw6ld0ivSicvYRTD42NZu5bWJnsa16F/sF/NflpBUXv/lzRI0Qg0oSTJEAmeyk8VYe7HCzvQKBgQCrvBO4m/kD8KbDnD4AU4vwY9LcPq2BNIR1XF90X+zeoG1/YcUMD5VFDIvHZ3AxBN1DjGiZ3rloTjmPrIqchfp2CGts0IrZwKOH+gNSr9erjG08pu/4oncAoyNdgF3nIeDHa/IcmDA8WwB9qXMZ+u3olu4gFN7l8xUoSWfSmZdOtQKBgA17Nr1qY7s9nGC6EZTba13f2/5bdGvWeG81ILaphmZYVaucUCF0K/2QvTrb2UWNjXfxqjJiPW1TbOyori9mTNWZsqT5mfeqaD26VF04vzmcqKYHOMefGG9Esg0LTjAzPONQD94nx9JCQ7Qs8dJdAo6v5PF+mup25ejK9Rcugn/pAoGAdEf2Je2tMwkBtvr0L+QvOkc/DCwPGTqUMKkiWIC35KlBsTeGapjNKZx73+bJBDt7ozfdcYtStRQR+pEeQ1jWWq6/L4i8WVQj0EGF776hwf6AwsiC6Y4v66wXzKLypFJlo0Fi+JAREhwweq2YJQ3EyLt2xmbidWm7acMkLL2u3bg=";

    // 3.支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmm2D1HGAfDLi4YUfU7sCr7D//mTD5cp5J50OXA6Lo0VeRkv/TauHcQ5wI8Hm4Wt0hFhaUIa+mUme2mtspaIQXkW0A8aLXABiKx5GV3mtQgCWTzF9BC+rTCj1NQ7/1sGbLx1iYar7N3R6ZiIXPFHbm8lyw+hIzG0yuFdTlwMpUerHwWm9DBWL6h6497uPApMe+nXpwy6k7PTgzttdgSN1VfdcI/wtucCGiKsaU4f5rqh9pmYGc7pTE+LrBeTu6UYj9q1fol47BP3KfCVC67Js8Yj9Sma66BBv4saaOzRazbUwjA+JuZFvarAr5q8HdHU+vtAZNEjR3xmy5fML1caK+wIDAQAB";

    // 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "https://pts.linkcld.com/pts-server-test/ticketService/alipay/notify";

    // 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String refund_notify_url = "https://pts.linkcld.com/pts-server-test/ticketService/alipay/refundNotify";

    // 5.请求网关地址
    public static String URL = "https://openapi.alipay.com/gateway.do";

    // 6.编码
    public static String CHARSET = "UTF-8";

    // 7.返回格式
    public static String FORMAT = "json";

    // 8.加密类型
    public static String SIGNTYPE = "RSA2";

}

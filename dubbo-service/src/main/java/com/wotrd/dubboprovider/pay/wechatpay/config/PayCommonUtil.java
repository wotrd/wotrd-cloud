package com.wotrd.dubboprovider.pay.wechatpay.config;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.util.*;
import java.util.logging.Logger;

public class PayCommonUtil {

    private static final Logger logger = Logger.getLogger(PayCommonUtil.class.getName());

    //微信参数配置
    private static String API_KEY = WechatConfig.APP_KEY;

    //随机字符串生成
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    //请求xml组装
    public static String getRequestXml(SortedMap<String, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(key) || "body".equalsIgnoreCase(key) || "sign".equalsIgnoreCase(key)) {
                sb.append("<" + key + ">" + "<![CDATA[" + value + "]]></" + key + ">");
            } else {
                sb.append("<" + key + ">" + value + "</" + key + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    //生成签名
    public static String createSign(String characterEncoding, SortedMap<String, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + API_KEY);
        logger.info("加密之前：" + sb.toString());
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

    /**
     * 验证回调签名
     *
     * @return
     */
    public static boolean isTenpaySign(Map<String, String> map) {
        String characterEncoding = "utf-8";
        String charset = "utf-8";
        String signFromAPIResponse = map.get("sign");
        if (signFromAPIResponse == null || signFromAPIResponse.equals("")) {
            logger.info("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
            return false;
        }
        logger.info("服务器回包里面的签名是:" + signFromAPIResponse);
        //过滤空 设置 TreeMap
        SortedMap<String, String> packageParams = new TreeMap();

        for (String parameter : map.keySet()) {
            String parameterValue = map.get(parameter);
            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }

        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();

        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (!"sign".equals(k) && null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + API_KEY);

        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        //算出签名
        String resultSign = "";
        String tobesign = sb.toString();

        if (null == charset || "".equals(charset)) {
            resultSign = MD5Util.MD5Encode(tobesign, characterEncoding).toUpperCase();
        } else {
            try {
                resultSign = MD5Util.MD5Encode(tobesign, characterEncoding).toUpperCase();
            } catch (Exception e) {
                resultSign = MD5Util.MD5Encode(tobesign, characterEncoding).toUpperCase();
            }
        }

        String tenpaySign = ((String) packageParams.get("sign")).toUpperCase();
        return tenpaySign.equals(resultSign);
    }

    //请求方法
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        try {

            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
            logger.info("连接超时：{}" + ce);
        } catch (Exception e) {
            logger.info("https请求异常：{}" + e);
        }
        return null;
    }

    //退款的请求方法
    public static String httpsRequest2(String requestUrl, String requestMethod, String outputStr) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        StringBuilder res = new StringBuilder("");
        FileInputStream instream = new FileInputStream(new File("/home/apiclient_cert.p12"));
        try {
            keyStore.load(instream, "".toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, "1313329201".toCharArray())
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

            HttpPost httpost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            StringEntity entity2 = new StringEntity(outputStr, Consts.UTF_8);
            httpost.setEntity(entity2);
            logger.info("executing request" + httpost.getRequestLine());

            CloseableHttpResponse response = httpclient.execute(httpost);

            try {
                HttpEntity entity = response.getEntity();

                logger.info(response.getStatusLine().toString());
                if (entity != null) {
                    logger.info("Response content length: " + entity.getContentLength());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text = "";
                    res.append(text);
                    while ((text = bufferedReader.readLine()) != null) {
                        res.append(text);
                        logger.info(text);
                    }

                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return res.toString();
    }

    //xml解析
    public static Map doXMLParse(String strxml) throws JDOMException, IOException {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        if (null == strxml || "".equals(strxml)) {
            return null;
        }

        Map m = new HashMap();

        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }

            m.put(k, v);
        }

        //关闭流
        in.close();

        return m;
    }

    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }


    public static String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }


}

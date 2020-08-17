package com.wotrd.dubboprovider.pay.wechatpay.config;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class XMLUtil {

    /**
     * @param params
     * @Author: HONGLINCHEN
     * @Description:请求值转换为xml格式 SortedMap转xml
     * @Date: 2017-9-7 17:18
     */
    public static String SortedMaptoXml(SortedMap<String, String> params) {
        StringBuilder sb = new StringBuilder();
        Set es = params.entrySet();
        Iterator it = es.iterator();
        sb.append("<xml>\n");
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            sb.append("<" + k + ">");
            sb.append(v);
            sb.append("</" + k + ">\n");
        }
        sb.append("</xml>");
        return sb.toString();
    }
}

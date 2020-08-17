package com.wotrd.dubboprovider.pay.wechatpay.config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class StringUtil {

    public static boolean checkStrIsNotNull(Object oriStr) {
        return oriStr != null && !oriStr.toString().isEmpty() && !oriStr.toString().equalsIgnoreCase("null");
    }

    public static boolean checkStrsIsNotNull(Object ...oriStr) {
        Boolean boo = true;
        for (Object obj:oriStr) {
            boo = obj != null && !obj.toString().isEmpty() && !obj.toString().equalsIgnoreCase("null");
            if(boo == false){
                return false;
            }
        }
        return boo;
    }

    public static List<Map> xmlToList(String result){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;
        InputSource source = null;
        StringReader reader = null;
        List<Map> resultMap = new ArrayList<>();
        try {
            builder = factory.newDocumentBuilder();
            reader = new StringReader(result);
            source = new InputSource(reader);//使用字符流创建新的输入源
            doc = builder.parse(source);
            Element element = doc.getDocumentElement();
            NodeList nodeList = element.getChildNodes();
            int total = 0;
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node1 = nodeList.item(i);
                if ("total".equals(node1.getNodeName())) {
                    total = Integer.parseInt(node1.getTextContent());
                }
                if ("rows".equals(node1.getNodeName())) {
                    if (total > 0) {
                        NodeList childNodes = node1.getChildNodes();
                        NodeList map = childNodes.item(0).getChildNodes();
                        for (int j = 0; j < map.getLength(); j++) {
                            NodeList data = map.item(j).getChildNodes();
                            Map xmlResult = new HashMap();
                            for (int h = 0; h < data.getLength(); h++) {
                                xmlResult.put(data.item(h).getNodeName(), data.item(h).getTextContent());
                            }
                            resultMap.add(xmlResult);
                        }
                        return resultMap;
                    } else {
                        return null;
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List arr = new ArrayList<>();
    }

    public static List<String> string2List(String str) {
        if (org.apache.commons.lang3.StringUtils.isNoneEmpty(str)) {
            String[] strList = str.split(",");
            return Arrays.asList(strList);
        }
        return null;
    }

    public static String GetMapToXML(Map<String, String> param) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        for (Map.Entry<String, String> entry : param.entrySet()) {
            sb.append("<" + entry.getKey() + ">");
            sb.append(entry.getValue());
            sb.append("</" + entry.getKey() + ">");
        }
        sb.append("</xml>");
        return sb.toString();
    }
}

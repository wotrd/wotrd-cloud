package com.wotrd.feign.controller;

import com.aisino.projects.service.newJk.util.DESDZFP;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: Test
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/10/28 19:36
 */
@Slf4j
public class Test {
    public static void main(String[] args) {

        formatPwd();

    }

    private static void formatPwd() {
        String SendUrl = "";
        String order = "{\"identity\":\"2329CC5F90EDAA8208F1F3C72A0CE72A713A9D425CD50CDE\",\"order\":{\"buyername\":\"浙江爱信诺\",\"taxnum\":\"124511234993295177\",\"phone\":\"0\",\"address\":\"浙江省杭州市万塘路\",\"account\":\"\",\"telephone\":\"0\",\"orderno\":\"nuonuo12345\",\"invoicedate\":\"2020-12-31 19:16:51\",\"clerk\":\"黄芝\",\"saleaccount\":\"宇宙行 442612010103507108\",\"salephone\":\"0774-7893911\",\"saleaddress\":\"富川瑶族自治县新 永路 138 号\",\"saletaxnum\":\"339901999999143\",\"kptype\":\"1\",\"message\":\"\",\"payee\":\"林莉苏\",\"checker\":\"林莉苏\",\"tsfs\":\"-1\",\"email\":\"502192347@qq.com\",\"qdbz\":\"0\",\"qdxmmc\":\"\",\"dkbz\":\"0\",\"deptid\":\"\",\"clerkid\":\"\",\"invoiceLine\":\"p\",\"cpybz\":\"\",\"detail\":[{\"goodsname\":\"苹果\",\"num\":\"1\",\"price\":\"1\",\"hsbz\":\"1\",\"taxrate\":\"0.13\",\"spec\":\"\",\"unit\":\"吨\",\"spbm\":\"10101150101\",\"zsbm\":\"\",\"fphxz\":\"0\",\"yhzcbs\":\"0\",\"zzstsgl\":\"\",\"lslbs\":\"\",\"kce\":\"\"}]}}";
        order = DESDZFP.encrypt(order);
        HttpClient httpclient = null;
        PostMethod post = null;
        try {
            httpclient = new HttpClient();
            post = new PostMethod(SendUrl);
            //设置编码方式
            post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            //添加参数
            post.addParameter("order", order);
            //执行
            httpclient.executeMethod(post);
            //接口返回信息
            String info = new String(post.getResponseBody(), "UTF-8");
            log.info("info:{}", info);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            //关闭连接，释放资源
            post.releaseConnection();
            ((SimpleHttpConnectionManager) httpclient.getHttpConnectionManager()).shutdown();
        }

    }

    private static String format() {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        int sum = list.stream().mapToInt(Integer::intValue).sum();
        Set<String> set = new HashSet<>();
//        set.add("tom");
//        set.add("bom");
//        set.add("校长");
//        set.add("校长");
        return StringUtils.collectionToDelimitedString(set, "、");
    }

    private static void jsonTest() {
        List<Test1> list = new ArrayList<>();
        Test1 test1 = new Test1();
        test1.setMap(new HashMap<>());
        List<Long> objects = Lists.newArrayList();
        objects.add(112L);
        test1.setIds(objects);
        list.add(test1);

        String s = JSONObject.toJSONString(list);
        System.out.println("s=" + s);
        List<com.wotrd.feign.controller.Test.Test1> list1 = JSONObject.parseArray(s, Test1.class);
        System.out.println("list1=" + list1);
    }


    @Data
    static class Test1 {
        Map<String, List> map;

        List<Long> ids;
    }

    private static void jsonFormatMap() {
        Test1 test1 = new Test1();
        Map<String, List> map = new HashMap<>();
        map.put("123", new ArrayList());
        test1.setMap(map);
        System.out.println(JSONObject.toJSON(test1));

    }

    private static void atomicTest() {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        int i = atomicInteger.incrementAndGet();
        int andIncrement = atomicInteger.getAndIncrement();

//        AtomicReference<Integer> reference = new AtomicReference<>();
//        boolean b = reference.compareAndSet(1, 3);
//        Integer integer = reference.get();
//        boolean b1 = reference.compareAndSet(1, 5);
//        Integer andAccumulate = reference.getAndAccumulate(1, new BinaryOperator<Integer>() {
//            @Override
//            public Integer apply(Integer integer, Integer integer2) {
//                return integer + integer2;
//            }
//        });
    }
}

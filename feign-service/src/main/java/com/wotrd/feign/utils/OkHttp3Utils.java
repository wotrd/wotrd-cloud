package com.wotrd.feign.utils;

import com.github.rholder.retry.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName: OkHttp3Utils
 * @Description: Okhttp3网络连接工具类
 * @author: wotrd
 * @date: 2019年5月17日上午10:47:14
 */
@Slf4j
public class OkHttp3Utils {

    private static Retryer<String> retryer = RetryerBuilder.<String>newBuilder()
            .retryIfResult(Objects::isNull)
            .retryIfExceptionOfType(ToRetryException.class)
            .withWaitStrategy(WaitStrategies.exponentialWait(200, 1000, TimeUnit.MILLISECONDS))
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .withRetryListener(new DoRetryListener())
            .build();

    //不允许创建对象
    private OkHttp3Utils() {
    }

    private static OkHttpClient client;

    static {
        client = new OkHttpClient.Builder()
                .readTimeout(20L, TimeUnit.SECONDS)
                .connectTimeout(20L, TimeUnit.SECONDS)
                .build();
    }


    /**
     * 使用get方式请求
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        //调用ok的get请求
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        return dealResponse(request);
    }

    /**
     * 使用post方式请求
     *
     * @param url   请求地址
     * @param param 请求参数
     */
    public static String post(String url, Map<String, String> param) {
        //调用ok的post请求
        FormBody.Builder formbody = new FormBody.Builder();
        if (param != null && !param.isEmpty()) {
            //上传参数
            for (String key : param.keySet()) {
                formbody.add(key, param.get(key));
            }
        }
        //创建请求体
        FormBody body = formbody.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)//请求体
                .build();
        return dealResponse(request);
    }

    /**
     * 使用json格式请求http
     *
     * @param url    请求地址
     * @param param  json请求参数
     * @param cookie cookie信息
     * @return
     */
    public static String postWithJsonParam(String url, String param, String cookie) {
        //创建请求体
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , param);
        Request request = new Request.Builder()
                .url(url)
                .header("Cookie", cookie)
                .post(requestBody)//请求体
                .build();
        return dealResponse(request);
    }


    /**
     * 使用json格式请求http
     *
     * @param url
     * @param param
     * @return
     */
    public static String postWithJsonParam(String url, String param) {
        //创建请求体
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , param);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)//请求体
                .build();
        return dealResponse(request);
    }


    /**
     * 处理请求返回值
     *
     * @param request
     * @return
     */
    private static String dealResponse(Request request) {
        return doHttpExecute(request);
    }

    private static String doHttpExecute(Request request) {
        Callable<String> callable = () -> {
            try {
                try (Response response = client.newCall(request).execute()) {
                    if (response.isSuccessful() && response.body() != null) {
                        return response.body().string();
                    }
                }
            } catch (Exception e) {
                log.error("###doHttpExecute-error!", e);
                throw new ToRetryException(e.getMessage());
            }
            return null;
        };
        try {
            String call = retryer.call(callable);
            log.info("doHttpExecute-result:[{}]", call);
            return call;
        } catch (RetryException | ExecutionException e) {
            String errorMsg = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
            log.error("execute-http-error:{}", errorMsg, e);
            throw new ToRetryException(e);
        }

    }

    public static class ToRetryException extends RuntimeException {
        ToRetryException(String message) {
            super(message);
        }

        ToRetryException(Throwable e) {
            super(e);
        }
    }

    public static class DoRetryListener implements RetryListener {
        @Override
        public <V> void onRetry(Attempt<V> attempt) {
            log.warn("###http-attemptNum:{},delaySinceFirstAttempt:{},exception{}",
                    attempt.getAttemptNumber(), attempt.getDelaySinceFirstAttempt(),
                    attempt.hasException() ? attempt.getExceptionCause() : "");
        }
    }
}

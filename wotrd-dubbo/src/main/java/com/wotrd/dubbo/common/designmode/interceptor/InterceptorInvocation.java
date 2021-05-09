package com.wotrd.dubbo.common.designmode.interceptor;

import com.wotrd.dubbo.client.domain.Result;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/5/9 20:55
 */
public class InterceptorInvocation {

    private List<Interceptor> interceptorList = new ArrayList<>();

    private Iterator<Interceptor> interceptors;

    private Target target;

    private Request request;

    public Result invoke(){
        if (interceptors.hasNext()){
            Interceptor interceptor = interceptors.next();
            interceptor.interceptor(this);
        }else {
            return target.execute(request);
        }
        return null;
    }

    public void addInterceptor(Interceptor interceptor){
        interceptorList.add(interceptor);
        interceptors = interceptorList.iterator();
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}

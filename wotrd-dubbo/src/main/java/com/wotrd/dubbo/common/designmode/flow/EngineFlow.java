package com.wotrd.dubbo.common.designmode.flow;

import lombok.Data;
import org.frameworkset.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/6/5 20:19
 */
public class EngineFlow<REQ, RES, CTX> {

    private List<Checker<REQ, RES, CTX>> checkerList = new ArrayList<>();
    private List<Processor<REQ, RES, CTX>> processorList = new ArrayList<>();
    private List<Processor<REQ, RES, CTX>> resultProcessorList = new ArrayList<>();
    private Function<Exception, RES> exceptionHandler;

    private Class<CTX> ctxClass;

    public RES start(REQ request) {
        FlowContext<REQ, RES, CTX> flowContext = new FlowContext();
        flowContext.init(ctxClass);
        flowContext.setRequest(request);
        // check
        try {
            check(flowContext);
        } catch (Exception e) {
            if (null != exceptionHandler) {
                exceptionHandler.apply(e);
            } else {
                throw e;
            }
        }
        // process
        try {
            process(flowContext);
        } catch (Exception e) {
            if (null != exceptionHandler) {
                exceptionHandler.apply(e);
            } else {
                throw e;
            }
        }
//        // result process
//        Optional.ofNullable(resultProcessorList).ifPresent(list -> list.stream().forEach(
//                flowContext.setResult(resultProcessor -> resultProcessor.process(flowContext))));

        return flowContext.getResult();

    }

    private void process(FlowContext<REQ, RES, CTX> flowContext) {
        Assert.notEmpty(processorList, "processorList is empty");

        processorList.stream().forEach(processor -> processor.process(flowContext));

    }

    private void check(FlowContext<REQ, RES, CTX> flowContext) {
        if (!CollectionUtils.isEmpty(checkerList)) {
            checkerList.stream().forEach(checker -> checker.check(flowContext));
        }
    }

    static class EngineFlowBuilder<REQ, RES, CTX> {

        private List<Checker<REQ, RES, CTX>> checkerList = new ArrayList<>();
        private List<Processor<REQ, RES, CTX>> processorList = new ArrayList<>();
        private Function<Exception, RES> exceptionHandler;

        private Class<CTX> ctxClass;

        public static EngineFlowBuilder builder() {
            return new EngineFlowBuilder();
        }

        public EngineFlowBuilder checker(Checker<REQ, RES, CTX> checker) {
            checkerList.add(checker);
            return this;
        }

        public EngineFlowBuilder processor(Processor<REQ, RES, CTX> processor) {
            processorList.add(processor);
            return this;
        }

        public EngineFlowBuilder exceptionHandler(Function<Exception, RES> handler) {
            this.exceptionHandler = handler;
            return this;
        }

        public EngineFlowBuilder contextClass(Class<CTX> ctx) {
            this.ctxClass = ctx;
            return this;
        }

        public EngineFlow build() {
            EngineFlow engineFlow = new EngineFlow();
            engineFlow.exceptionHandler = exceptionHandler;
            engineFlow.checkerList = checkerList;
            engineFlow.processorList = processorList;
            engineFlow.ctxClass = ctxClass;
            return engineFlow;
        }

    }


}

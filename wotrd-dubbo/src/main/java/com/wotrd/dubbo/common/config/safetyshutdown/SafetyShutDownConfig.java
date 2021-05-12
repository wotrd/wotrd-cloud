package com.wotrd.dubbo.common.config.safetyshutdown;


import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @description: 线程池优雅停机
 * @Author: wotrd
 * @date: 2021/5/12 15:41
 */
@Slf4j
@Configuration
public class SafetyShutDownConfig implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private volatile Connector connector;
    private final int waitTime = 30;

    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        this.connector.pause();
        Executor executor = this.connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            try {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                threadPoolExecutor.shutdown();
                if (!threadPoolExecutor.awaitTermination(waitTime, TimeUnit.SECONDS)) {
                    log.warn("Tomcat thread pool did not shut down gracefully within " + waitTime + " seconds. Proceeding with forceful shutdown");
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}


//    public static void main( String[] args ) {
//        ShutdownHook shutdownHook = new ShutdownHook(new Thread());
//        System.out.println( "Hello World!" );
//        while(!shutdownHook.shouldShutDown()){
//            System.out.println("I am sleep");
//            try {
//                Thread.sleep(1*1000);
//            } catch (InterruptedException e) {
//                System.out.println("execute() interrupted");
//            }
//            System.out.println("I am not sleep");
//        }
//        System.out.println("end of execute()");
//        System.out.println( "End of main()" );
//    }


//public static class ShutdownHook extends Thread {
//    private Thread mainThread;
//    private boolean shutDownSignalReceived;
//
//    @Override
//    public void run() {
//        System.out.println("Shut down signal received.");
//        this.shutDownSignalReceived = true;
//        mainThread.interrupt();
//        try {
//            mainThread.join(); //当收到停止信号时，等待mainThread的执行完成
//        } catch (InterruptedException e) {
//        }
//        System.out.println("Shut down complete.");
//    }
//
//    public ShutdownHook(Thread mainThread) {
//        super();
//        this.mainThread = mainThread;
//        this.shutDownSignalReceived = false;
//        Runtime.getRuntime().addShutdownHook(this);
//    }
//
//    public boolean shouldShutDown() {
//        return shutDownSignalReceived;
//    }



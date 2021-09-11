package com.wotrd.wotrdnetty.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @description: TODO
 * @Author woniu
 * @date 2021/9/11 2:48 下午
 */
@Slf4j
@Service
public class ServerSocketService {

    public void startServerSocketBlock() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(8081));
            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int read;
                do {
                    read = socketChannel.read(byteBuffer);
                    log.info("read msg:count:{}, body:{}", read, byteBuffer.get());
                } while (read != -1);
            }

        } catch (IOException e) {
            log.error("startServerSocket failed", e);
        }
    }

    public void startServerSocketNoBlock() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(9999));
            serverSocketChannel.configureBlocking(false);
            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (null != socketChannel) {

                }
            }

        } catch (IOException e) {
            log.error("startServerSocket failed", e);
        }
    }

    public void connectServerSocket() {

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.socket().connect(new InetSocketAddress(8081), 60);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            String body = "你好，我来了， 😊";
            byteBuffer.put(body.getBytes());
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                socketChannel.write(byteBuffer);
            }

        } catch (IOException e) {
            log.error("connect server failed", e);
        }

    }

    public void sinkChannel() throws IOException {
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        Pipe.SinkChannel sinkChannel = Pipe.open().sink();
        while (buf.hasRemaining()) {
            sinkChannel.write(buf);
        }
    }

    public void sourceChannel() throws IOException {

        Pipe.SourceChannel sourceChannel = Pipe.open().source();
        sourceChannel.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read;
        do {
            read = sourceChannel.read(byteBuffer);
            log.info("source channel:{}", byteBuffer.get());
        } while (read != -1);
    }
}

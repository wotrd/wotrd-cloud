package com.wotrd.wotrdnetty.web;

import com.wotrd.wotrdnetty.service.ServerSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO
 * @Author woniu
 * @date 2021/9/11 3:00 下午
 */
@RestController
public class SocketChannelController {

    @Autowired
    private ServerSocketService socketChannelTest;

    @RequestMapping("startServerSocket")
    public String startServerSocketChannel(){
        socketChannelTest.startServerSocketChannel();
        return "success";
    }

    @RequestMapping("connectServerSocket")
    public String connectServerSocket(){
        socketChannelTest.connectServerSocket();
        return "success";
    }

}

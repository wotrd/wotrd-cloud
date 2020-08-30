package com.wotrd.gateway.listener;

import com.wotrd.gateway.model.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: EventListner
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/6/27 20:43
 */
@Slf4j
@Component
public class UserEventListener {

    @EventListener(condition = "#userDTO.userName!=null")
    public void listener(UserDTO userDTO) {
        log.info("接收到事件{}", userDTO);
    }

}

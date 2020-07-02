package com.wotrd.gateway.service;

import com.wotrd.gateway.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @ClassName: EventService
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/6/27 20:37
 */
@Service
public class EventService {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void publish() {
        UserDTO userDTO = UserDTO.builder()
                .password("123")
                .userName("234")
                .build();
        publisher.publishEvent(userDTO);
        publisher.publishEvent(new RefreshRoutesEvent(null));
    }

}

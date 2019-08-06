package com.wotrd.kafkatemplate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private Long id;

    private String msg;

    private Date sendTime;


}

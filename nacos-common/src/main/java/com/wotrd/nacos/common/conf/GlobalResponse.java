package com.wotrd.nacos.common.conf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalResponse<T> {

    private String code;

    private String msg;

    private T data;

}

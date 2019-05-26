package com.wotrd.nacos.common.conf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalRequestBody<T> {

    @Valid
    private GlobalRequestHead h;

    @Valid
    private T b;

}

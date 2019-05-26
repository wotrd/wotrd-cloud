package com.wotrd.nacos.common.conf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalRequestBody<T> {

    @Valid
    @NotNull(message = "h:请求头格式错误")
    private GlobalRequestHead h;

    @Valid
    @NotNull(message = "b:请求体格式错误")
    private T b;

}

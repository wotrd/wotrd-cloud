package com.wotrd.nacos.common.conf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalRequestHead {

    @NotNull(message = "token格式错误")
    private String token;
    @NotBlank(message = "appCode格式错误")
    private String appCode;
    @NotEmpty(message = "userId格式错误")
    private String userId;
    @NotEmpty(message = "deviceType格式错误")
    private String deviceType;

}

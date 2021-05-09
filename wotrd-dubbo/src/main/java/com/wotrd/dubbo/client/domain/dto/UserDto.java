package com.wotrd.dubbo.client.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.dubbo.apidocs.annotations.ResponseProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @ClassName: User
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/6/20 20:41
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private Long id;

    @ResponseProperty(value = "用户名字", example = "500")
    private String userName;
    @ResponseProperty(value = "密码", example = "500")
    private String password;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;

    private Address address;

}



package com.wotrd.gateway.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserDTO
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/6/27 20:39
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String userName;

    private String password;

}

package com.wotrd.springsecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName: Test
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/5/3 12:17
 */
public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode("myPassword");
        boolean myPassword = encoder.matches("myPassword", result);
        System.out.printf("result:" + result + "");
    }
}

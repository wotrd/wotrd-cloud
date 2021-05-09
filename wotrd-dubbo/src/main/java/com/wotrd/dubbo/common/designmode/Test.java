package com.wotrd.dubbo.common.designmode;

import com.wotrd.dubbo.client.domain.dto.Address;
import com.wotrd.dubbo.client.domain.dto.Book;
import com.wotrd.dubbo.client.domain.dto.UserDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/4/18 20:22
 */
public class Test {
    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
//        String a = map.put("a", "a");
//        String b = map.put("a", "b");
//        System.out.println("test map");
//        test(d -> System.out.println("hahah"));
//        Book book = new Book();
//        System.out.println(book.getNum()+1);
//        System.out.println("test");
        UserDto userDto = new UserDto();
        Optional.ofNullable(userDto).map(UserDto::getAddress).map(Address::getProvince).orElse("");
//        List<Address> addresses = Optional.ofNullable(userDto).map(UserDto::getAddress).orElse(null);
    }


    public static void test(Consumer consumer) {
        consumer.accept(consumer);


    }
}

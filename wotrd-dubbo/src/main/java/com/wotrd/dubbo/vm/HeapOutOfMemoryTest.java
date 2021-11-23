package com.wotrd.dubbo.vm;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/11/16 23:17
 */
public class HeapOutOfMemoryTest {

    /**
     * java堆溢出虚拟机命令
     * VM args: -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
     * @param args
     */
    public static void main(String[] args) {
        List<HeapOutOfMemoryTest> list = new ArrayList<>();
        int i=0;
        while (true){
            list.add(new HeapOutOfMemoryTest());
            i++;
            if (i>2000){
                i=0;
                list = new ArrayList<>();
            }

        }
    }


}

package com.xupt.java2;

import java.util.Arrays;
import java.util.List;

/**
 * @author Wnlife
 * @create 2019-10-16 19:56
 */
public class TestLambda {


    public void test1(){

        List<String> list=Arrays.asList("哈哈","嘿嘿","恩恩","哒哒","默默","鬼鬼");
        list.stream().forEach(System.out::println);
    }

    public void test2(){

//        Collections.sort();

        System.out.println("测试用例2");

    }
}

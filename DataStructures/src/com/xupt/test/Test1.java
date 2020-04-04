package com.xupt.test;

import java.util.Arrays;
import java.util.function.BiPredicate;

/**
 * @author Wnlife
 * @create 2019-12-07 10:44
 */
public class Test1 {

    public static void main(String[] args) {

        String str="abc";
        byte[] bytes = str.getBytes();
        for (byte b : bytes) {
            int t= (int) b;
            String s = Integer.toBinaryString(t);
            System.out.println(s);
            int i = Integer.parseInt(s, 2);
            System.out.println(i);
        }
        char[] chars = str.toCharArray();
        System.out.println(Arrays.toString(bytes));
        System.out.println(Arrays.toString(chars));
        BiPredicate<String, String> bp=(s1, s2)->s1.equals(s2);

//        Stream.iterate()
//        Stream.generate()
    }
}

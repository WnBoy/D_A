package com.xupt.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Wnlife
 * @create 2019-12-25 17:44
 */
public class Test8 {

    public static void main(String[] args) {

        int k=nextHashCode2();
        System.out.println(k);

        System.out.println(k);
    }
    private static AtomicInteger nextHashCode =new AtomicInteger();
    private static final int HASH_INCREMENT = 0x61c88647;
    private static int nextHashCode2() {
      return nextHashCode.getAndAdd(HASH_INCREMENT);
    }


}

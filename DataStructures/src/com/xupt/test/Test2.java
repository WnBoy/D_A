package com.xupt.test;

import java.util.Properties;

/**
 * @author Wnlife
 * @create 2019-12-07 11:12
 */
public class Test2 {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        properties.list(System.out);
    }
}

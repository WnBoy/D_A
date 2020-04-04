package com.xupt.test;

import java.util.HashMap;

/**
 * @author Wnlife
 * @create 2019-12-20 10:35
 */
public class Test6 {

    public static void main(String[] args) {
        HashMap<String,Integer>map=new HashMap<>(16);
        map.put("hah",1);
        map.put("da",1);
        map.put("das",1);
        map.forEach((k,v)->{
            System.out.println(k+":"+v);
        });
    }

}

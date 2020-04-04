package com.xupt.test;

/**
 * @author Wnlife
 * @create 2019-12-25 12:43
 */
public class test7 {

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal1 = ThreadLocal.withInitial(() -> "aaa");
        ThreadLocal<String> threadLocal=new ThreadLocal<String>(){
            @Override
            protected String initialValue() {
                return "aaa";
            }
        };
//        threadLocal.set("aaa");

        ThreadLocal<String> threadLocal2 = ThreadLocal.withInitial(() -> "bbb");


        System.out.println(threadLocal1.get());
//        threadLocal.set("bbb");
        System.out.println(threadLocal2.get());
//        threadLocal.set("ccc");



//        System.out.println(threadLocal.get());
//        threadLocal.set("ddd");
//        System.out.println(threadLocal.get());
//        threadLocal.remove();
//        System.out.println(threadLocal.get());

    }
}

package com.xupt.recursion;

/**
 * @author Wnlife
 * @create 2019-10-29 16:01
 *
 * 递归小测试
 */
public class RecursionTest {

    public static void main(String[] args) {

//        test1(5);
        factorial(5);
    }

    public static void test1(int n){
        if(n>2){
            test1(n-1);
        }
        System.out.println("n = " + n);
    }

    //阶乘
    public static int factorial(int n){

        if(n==1)
            return 1;
        else
            return factorial(n-1)*n;
    }
}

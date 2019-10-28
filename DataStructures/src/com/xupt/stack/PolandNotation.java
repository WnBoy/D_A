package com.xupt.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author Wnlife
 * @create 2019-10-27 19:16
 * <p>
 * 逆波兰计算器:根据输入的波兰表达式求出最终的值
 */
public class PolandNotation {

    public static void main(String[] args) {

        //先定义给逆波兰表达式
        //(30+4)×5-6  => 30 4 + 5 × 6 - => 164
        // 4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / +
        //测试
        //说明为了方便，逆波兰表达式 的数字和符号使用空格隔开
        String suffixExpression = "30 4 + 5 * 6 -";
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +"; // 76

        List<String> list = getListString(suffixExpression);
        int req = calculate(list);
        System.out.println("req = " + req);


    }

    //将一个逆波兰表达式， 依次将数据和运算符 放入到 ArrayList中
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression分割
        String[] strings = suffixExpression.split(" ");
        List<String> list = Arrays.asList(strings);
        return list;
    }

    //完成对逆波兰表达式的运算
	/*
	    1)从左至右扫描，将3和4压入堆栈；
		2)遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
		3)将5入栈；
		4)接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
		5)将6入栈；
		6)最后是-运算符，计算出35-6的值，即29，由此得出最终结果
	 */
    public static int calculate(List<String> list) {
        Stack<String>stack=new Stack<>();
        for (String s : list) {
            //如果当前数据是一个数
            if(s.matches("\\d+")){
                stack.push(s);
            }else {//当前元素是一个符号
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int req=0;
                switch (s){
                    case "+":
                        req=num1+num2;
                        break;
                    case "-":
                        req=num1-num2;
                        break;
                    case "*":
                        req=num1*num2;
                        break;
                    case "/":
                        req=num1/num2;
                        break;
                    default:
                        throw new RuntimeException("符号有问题");
                }
                stack.push(req+"");
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

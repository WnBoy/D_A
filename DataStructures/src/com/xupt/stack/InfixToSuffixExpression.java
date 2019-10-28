package com.xupt.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Wnlife
 * @create 2019-10-27 21:05
 * <p>
 * 中缀表达式 -> 后缀表达式
 */
public class InfixToSuffixExpression {

    public static void main(String[] args) {
        /*
        完成将一个中缀表达式转成后缀表达式的功能
        说明:
        1. 1+((2+3)*4)-5 => 转成  1 2 3 + 4 * + 5 –
        2. 因为直接对str 进行操作，不方便，因此 先将  "1+((2+3)*4)-5" -> 中缀的表达式对应的List
        即 "1+((2+3)×4)-5" -> ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        3. 将得到的中缀表达式对应的List -> 后缀表达式对应的List
           即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  -> ArrayList [1,2,3,+,4,*,+,5,–]
        */

        String infixExpression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = InfixToList(infixExpression);
        System.out.println("中缀表达式对应的list = " + infixExpressionList);//[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        List<String> suffixExpreesionList = parseSuffixExpreesionList(infixExpressionList);
        System.out.println("后缀表达式对应的list = "+suffixExpreesionList);//[1, 2, 3, +, 4, *, +, 5, -]

    }


    //方法：将得到的中缀表达式对应的List -> 后缀表达式对应的List
    //即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  -> ArrayList [1,2,3,+,4,*,+,5,–]
    public static List<String> parseSuffixExpreesionList(List<String>infixList){

        //初始化两个栈
        Stack<String>s1=new Stack<>();//代表运算符栈
        List<String>s2=new ArrayList<>();//代表中间结果栈   （注意：此处使用）

        for (String s : infixList) {
            if(s.matches("\\d+")){//如果是操作数，直接压入栈s2
                s2.add(s);
            }else if (s.equals("(")){//如果是左括号直接入栈是s1
                s1.push(s);
            }else if (s.equals(")")){//如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.isEmpty()&&!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//丢弃左括号
            }else {//如果是运算符
                if(s1.isEmpty()||s1.peek().equals("(")){//如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
                    s1.push(s);
                }else if (Operation.getPriority(s)>Operation.getPriority(s1.peek())){//否则，若优先级比栈顶运算符的高，也将运算符压入s1；
                    s1.push(s);
                }else {//否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较；
                    while (!s1.isEmpty()&&Operation.getPriority(s)<=Operation.getPriority(s1.peek())){
                        s2.add(s1.pop());
                    }
                    s1.push(s);//还需要将s压入栈
                }
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while (!s1.isEmpty()){
            s2.add(s1.pop());

        }
        return s2;//注意因为是存放到List, 因此按顺序输出就是对应的后缀表达式对应的List
    }


    //将中缀表达式转成List
    public static List<String> InfixToList(String str) {

        ArrayList<String> list = new ArrayList<>();
        int i = 0;//这时是一个指针，用于遍历 中缀表达式字符串
        String keepNum ;// 对多位数的拼接
        char c;// 每遍历到一个字符，就放入到c
        do {
            c = str.charAt(i);
            if (c < 48 || c > 57) {//说明当前是一个操作符，直接入集合
                list.add(c + "");
                i++;
            } else {//如果是个数，考虑【多位数】的情况
                keepNum = "";
                while (i < str.length() && (c = str.charAt(i)) >= 48 && (c = str.charAt(i)) <= 57) {
                    keepNum += c;
                    i++;
                }
                list.add(keepNum);
            }
        } while (i < str.length());
        return list;
    }


}

//编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation{

    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;

    //返回操作符的优先级
    public static int getPriority(String operation){
        int req=0;
        switch (operation){
            case "+":
                req=ADD;
                break;
            case "-":
                req=SUB;
                break;
            case "*":
                req=MUL;
                break;
            case "/":
                req=DIV;
                break;
            default:
                System.out.println("不存在的操作符:"+operation);
                break;
        }
        return req;
    }
}
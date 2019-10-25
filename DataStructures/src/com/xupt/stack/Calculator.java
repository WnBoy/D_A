package com.xupt.stack;

/**
 * @author Wnlife
 * @create 2019-10-21 19:03
 * <p>
 * 使用数组模拟的栈实现计算器功能（加减乘除）
 */
public class Calculator {


    //实现表达式的计算++
    public static void main(String[] args) {

        String expression="7*2*2-5+1-5+3-4";

        //创建两个栈，一个是操作数栈，一个是操作符栈
        ArrayStack2 numStack=new ArrayStack2(10);
        ArrayStack2 operStack=new ArrayStack2(10);

        //定义需要的相关变量
        char ch=' ';//将每次扫描得到char保存到ch
        String keepNum="";
        int res = 0;//计算结果

        //循环取出每个数字和字符
        for (int i = 0; i < expression.length(); i++) {
            //取出每个字符
            ch = expression.charAt(i);
            //判断ch是什么，分别处理
            if(numStack.isOper(ch)){//是操作符
                //判断当前操作符栈是否为空
                if(operStack.isEmpty()){//如果为空直接压入操作符栈
                    operStack.push(ch);
                }else {//如果栈不为空
                    //当前字符的优先级大于栈顶字符的优先级，直接压入操作符栈
                    if(operStack.priority(ch)>operStack.priority(operStack.peek())){
                        operStack.push(ch);
                    }else {//如果当前字符的优先级小于或者等于栈顶的操作符
                        //从操作数栈取出两个数，从操作符栈中取出一个操作符，进行计算，
                        // 把计算结果放到操作数栈，把当前字符压入操作数栈
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        int opr = operStack.pop();
                        res = operStack.cal(num1, num2, opr);
                        //把运算的结果如数栈
                        numStack.push(res);
                        //然后将当前的操作符入符号栈
                        operStack.push(ch);
                    }
                }
            }else{//当前字符是个数
                //分析思路
                //1. 当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2. 在处理数，需要向expression的表达式的index 后再看一位,如果是数就进行扫描，如果是符号才入栈
                //3. 因此我们需要定义一个变量 字符串，用于拼接
                keepNum+=ch;
                //如果是最后一位，直接入栈
                if(i==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{//如果不是最后一位，则判断后一位是不是数字
                    if(operStack.isOper(expression.charAt(i+1))){
                        //如果下一位是运算符，则把当前的keepNum直接压入操作数栈
                        numStack.push(Integer.parseInt(keepNum));
                        //重要的!!!!!!, keepNum清空
                        keepNum="";
                    }
                }
            }
        }

        //当表达式扫描完毕后，就顺序的从操作数栈和操作符栈里面取出数据进行运算，把最终结果放入到操作数栈
        while (!operStack.isEmpty()){
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            int opr = operStack.pop();
            res = operStack.cal(num1, num2, opr);
            //把运算的结果如数栈
            numStack.push(res);
        }
        //操将数栈的最后数，pop出，就是结果
        int r = numStack.pop();
        System.out.printf("表达式 %s = %d",expression,r);

    }

}


/**
 * 用数组实现的栈，对原有的功能进行了扩展
 */
class ArrayStack2 {

    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，存储数据的地方
    private int top = -1;//栈顶

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //判断栈是否为空
    public boolean isEmpty() {
        return top == -1;
    }

    //判断栈是否满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //查看栈顶的值
    public int peek() {
        return stack[top];
    }

    //入栈-push
    public void push(int num) {

        if (isFull()) {
            System.out.println("栈是满的~~");
            return;
        }
        stack[++top] = num;
    }

    //出栈-pop
    public int pop() {

        if (isEmpty()) {
            throw new RuntimeException("栈是空的~~");
        }
        int val = stack[top];
        top--;
        return val;
    }

    //显示栈的情况[遍历栈]， 遍历时，需要从栈顶开始显示数据
    public void showStack() {

        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级，优先级是程序员来确定, 优先级使用数字表示
    //数字越大，则优先级就越高.
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        }
        return -1;
    }

    //判断是不是运算符
    public boolean isOper(int oper){
        return oper=='+'||oper=='-'||oper=='*'||oper=='/';
    }

    //计算两个数的方法
    public int cal(int num1, int num2, int oper) {
        int req = 0;
        switch (oper) {
            case '*':
                req = num2 * num1;
                break;
            case '/':
                req = num2 / num1;
                break;
            case '+':
                req = num2 + num1;
                break;
            case '-':
                req = num2 - num1;
                break;
            default:
                break;
        }
        return req;
    }

}
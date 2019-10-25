package com.xupt.stack;

import java.util.Scanner;

/**
 * @author Wnlife
 * @create 2019-10-20 21:29
 * <p>
 * 用数组实现栈
 */
public class ArrayStackDemo {

    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(5);
        String key = "";
        boolean loop = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop) {

            System.out.println("show:表示显示栈内的数据");
            System.out.println("push:表示添加数据到栈");
            System.out.println("pop:将栈顶的数据弹出来");
            System.out.println("exit:退出程序");

            key = scanner.next();
            switch (key) {
                case "show":
                    stack.showStack();
                    break;
                case "push":
                    System.out.println("请输入要添加的数据~~");
                    int val = scanner.nextInt();
                    stack.push(val);
                    break;
                case "pop":
                    try {
                        System.out.printf("出栈的数据为%d\n", stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~~");
    }
}


/**
 * 用数组实现的栈
 */
class ArrayStack {

    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，存储数据的地方
    private int top = -1;//栈顶

    public ArrayStack(int maxSize) {
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

}
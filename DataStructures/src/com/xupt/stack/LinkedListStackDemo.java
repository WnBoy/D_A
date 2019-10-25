package com.xupt.stack;

import java.util.Scanner;

/**
 * @author Wnlife
 * @create 2019-10-21 15:23
 * <p>
 * 用链表实现一个栈
 */
public class LinkedListStackDemo {

    public static void main(String[] args) {

        LinkedListStack stack = new LinkedListStack(5);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;//循环控制变量
        String key = "";
        while (loop) {

            System.out.println("show:表示查看栈内的数据~~");
            System.out.println("push:表示将数据压入栈内~~");
            System.out.println("pop:表示将栈顶的数据弹出~~");
            System.out.println("exit:表示退出程序~~");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.show();
                    break;
                case "push":
                    System.out.println("请输入数据:");
                    int val = scanner.nextInt();
                    stack.push(val);
                    break;
                case "pop":
                    try {
                        int topVal = stack.pop();
                        System.out.printf("出栈的数据是%d\n", topVal);
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
        System.out.println("程序退出~~");
    }

}


/**
 * 使用链表创建栈
 */
class LinkedListStack {

    private Node top;
    private int maxSize;//栈的大小
    private int size;

    public LinkedListStack(int maxSize) {
        this.maxSize = maxSize;
        create(maxSize);
    }

    //创建栈
    public void create(int num) {
        Node pre = null;
        for (int i = 0; i < num; i++) {
            Node node = new Node();
            if (i == 0) {
                top = node;
            } else {
                pre.next = node;
            }
            pre = node;
        }
    }

    //判断栈是否是满的
    public boolean isFull() {
        return size == maxSize;
    }

    //判断栈是不是为空
    public boolean isEmpty() {
        return size == 0;
    }

    //将数据压入栈
    public void push(int num) {
        if (isFull()) {
            System.out.println("栈是满的~~");
            return;
        }
        Node node = new Node(num);
        node.next = top;
        top = node;
        size++;
    }

    //将栈顶的数据弹出
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈内的数据为空~~");
        }
        int val = top.val;
        top = top.next;
        size--;
        return val;
    }

    //显示栈内的数据
    public void show() {
        if (isEmpty()) {
            System.out.println("栈为空~~");
            return;
        }

        Node temp = top;
        for (int i = 0; i < size; i++) {
            System.out.printf("stack[%d]=%d\n",i,temp.val);
            temp = temp.next;
        }
    }
}


/**
 * 链表节点
 */
class Node {

    public int val;
    public Node next;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}

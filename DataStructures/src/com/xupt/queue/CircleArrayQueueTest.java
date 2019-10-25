package com.xupt.queue;

import java.util.Scanner;

/**
 * @author Wnlife
 *
 * 测试2：环形队列
 */
public class CircleArrayQueueTest {

    public static void main(String[] args) {

        //先创建一个队列
        CircleArrayQueue queue=new CircleArrayQueue(4);
        char key=' ';//接受用户输入
        Scanner in=new Scanner(System.in);
        boolean loop=true;

        while(loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key=in.next().charAt(0);

            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    in.close();
                    loop=false;
                    break;
                case 'a':
                    System.out.print("请输入一个数：");
                    int t = in.nextInt();
                    queue.addQueue(t);
                    break;
                case 'g':
                    try {
                        int val = queue.getQueue();
                        System.out.printf("取出的数据为：%d\n",val);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n",head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}

package com.xupt.queue;

/**
 * @author Wnlife
 * @create 2019-10-17 18:05
 *
 * 数组模拟队列
 */
public class ArrayQueue {

    private int maxSize;//表示数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[]arr;//该数据用于存放数据，模拟队列

    //创建构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.front=-1;//指向队列的头部，分析出front是指向队列头的前一个位置
        this.rear=-1;//指向队列的尾部，指向队列的最后一个数据
        arr=new int[maxSize];
    }

    //判断队列是否已满
    public boolean isFull(){

        return rear==maxSize-1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }

    //添加数据到队列
    public void addQueue(int n){
        if (isFull()) {
            System.out.println("队列已满~~");
            return;
        }
        arr[++rear]=n;
    }

    //获取队列里面的数据
    public int getQueue(){
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据~~");
        }
        return arr[++front];
    }

    //显示对列里面的所有数据
    public void showQueue(){

        if (isEmpty()) {
            System.out.println("队列空的，没有数据~~");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    //显示队列的头数据，注意不是取数据
    public int headQueue(){

        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front+1];
    }

}

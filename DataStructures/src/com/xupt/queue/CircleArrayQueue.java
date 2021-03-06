package com.xupt.queue;

/**
 * @author Wnlife
 * @create 2019-10-17 20:49
 *
 * 数组模拟环形队列
 */
public class CircleArrayQueue {

    private int maxSize;//表示数组的最大容量
    //front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
    //front 的初始值 = 0
    private int front;
    //rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
    //rear 的初始值 = 0
    private int rear;
    private int[]arr;//该数据用于存放数据，模拟队列

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr=new int[maxSize];
    }

    //判断队列是否已满
    public boolean isFull(){

        return (rear+1)%maxSize==front;
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
        //直接将数据加入
        arr[rear]=n;
        //将 rear 后移, 这里必须考虑取模
        rear=(rear+1)%maxSize;
    }

    //获取队列里面的数据
    public int getQueue(){
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据~~");
        }
        // 这里需要分析出 front是指向队列的第一个元素
        // 1. 先把 front 对应的值保留到一个临时变量
        // 2. 将 front 后移, 考虑取模
        // 3. 将临时保存的变量返回
        int t=arr[front];
        front=(front+1)%maxSize;
        return t;
    }

    //显示对列里面的所有数据
    public void showQueue(){

        if (isEmpty()) {
            System.out.println("队列空的，没有数据~~");
            return;
        }
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }

    //计算当前队列有效数据的个数
    public int size(){

        return (rear+maxSize-front)%maxSize;
    }

    //显示队列的头数据，注意不是取数据
    public int headQueue(){

        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }

}

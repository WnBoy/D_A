package com.xupt.linkedlist;

/**
 * @author Wnlife
 * @create 2019-10-20 18:07
 */
public class Josepfu {

    public static void main(String[] args) {
        CircleSingletonLinkedList circleSingletonLinkedList = new CircleSingletonLinkedList();
        circleSingletonLinkedList.addBoy(5);
        circleSingletonLinkedList.showBoy();

        circleSingletonLinkedList.countBoy(1,2,5);

    }

}

/**
 * 创建一个环形的单向链表
 */
class CircleSingletonLinkedList {

    // 创建一个first节点,当前没有编号
    private Boy first = null;

    // 添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {

        // nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确~~");
            return;
        }
        Boy cur = null;// 辅助指针，帮助构建环形链表
        // 使用for来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.next = first;
                cur = boy;
            } else {
                cur.next = boy;
                boy.next = first;
                cur = boy;
            }
        }
    }


    /**
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {

        //对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误， 请重新输入~~");
            return;
        }
        //创建要给辅助指针，帮助小孩出圈
        Boy helper = first;
        while (helper.next != first) {//将helper指针移动到链表最后一个节点
            helper = helper.next;
        }
        //让helper和first指向起始点
        for (int i = 0; i < startNo-1; i++) {
            helper=helper.next;
            first=first.next;
        }
        //当小孩报数时，让first 和 helper 指针同时 的移动  m  - 1 次, 然后出圈
        //这里是一个循环操作，知道圈中只有一个节点
        while (helper != first) {
            for (int i = 0; i < countNum - 1; i++) {
                helper=helper.next;
                first=first.next;
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.no);
            Boy t=first;
            //这时将first指向的小孩节点出圈
            first=first.next;
            helper.next=first;
            t=null;
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.no);
    }


    // 遍历当前的环形链表
    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("没有小孩~~");
            return;
        }
        // 因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy cur = first;
        do {
            System.out.printf("小孩的编号 %d \n", cur.no);
            cur = cur.next;
        } while (cur != first);

    }

}


/**
 * 创建一个Boy类，表示一个链表的节点
 */
class Boy {

    public int no;//编号
    public Boy next;//下一个节点的指针

    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
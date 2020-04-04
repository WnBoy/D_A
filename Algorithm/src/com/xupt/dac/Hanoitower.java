package com.xupt.dac;

/**
 * @author Wnlife
 * @create 2020-02-01 20:06
 * <p>
 * 分治算法实例：汉诺塔问题
 */
public class Hanoitower {

    public static int count=0;
    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');
        System.out.println("一共需要移动"+count+"次");
    }

    public static void hanoiTower(int num, char a, char b, char c) {
        count++;
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            //如果有多个盘，将多个盘分为两部分：1.最底下的盘；2.上面的盘
            //1.先把 最上面的所有盘 A->B， 移动过程会使用到 c
            hanoiTower(num - 1, a, c, b);
            //2.把最下边的盘 A->C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            //3.把B塔的所有盘 从 B->C , 移动过程使用到 a塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}

package com.xupt.recursion;

/**
 * @author Wnlife
 * @create 2019-10-29 20:45
 * <p>
 * 8皇后问题解法:
 * 一共有92中摆放方法,一共判断了15720次冲突
 */
public class Queue8 {

    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义一个数组，存储皇后摆放位置的结果，比如：array={0, 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];
    static int count = 0;//摆放方法个数
    static int judgeCount = 0;//判断冲突次数

    public static void main(String[] args) {

        Queue8 queue8 = new Queue8();
        //从第0个皇后开始摆放
        queue8.check(0);
        System.out.printf("一共有%d中摆放方法,", count);
        System.out.printf("一共判断了%d次冲突", judgeCount);
    }

    /**
     * 放置第n个皇后
     *
     * @param n 代表第n个皇后
     */
    private void check(int n) {
        //当前方案的所有皇后已经放置好，可以输出一个这个摆放方式
        if (n == 8) {
            print();
            return;
        }
        //依次放入皇后，判断是否冲突  ,max代表棋盘最大的列数，每个皇后从每次从棋盘的第一列开始判断
        for (int i = 0; i < max; i++) {
            array[n] = i;//将第n个皇后摆放到第n列
            if (judge(n)) {//如果不冲突，则继续摆放下一个皇后
                check(n + 1);//递归摆放第n+1个皇后
            }
            //如果冲突，摆放到下一列，如果 i 循环到最大列数这个皇后还冲突，
            // 则跳转到上一个皇后，让上一个皇后后移下一列，然后判断是否冲突，
            // 上一个皇后不冲突后则继续向后，到下一个皇后，依次循环和递归判断
        }
    }

    /**
     * 判断第n个皇后排放的位置和前面几个皇后摆放的位置是否冲突
     * array[i]==array[n] 用来判断第n个皇后和前面的皇后是否在同一列
     * Math.abs(n-i)==Math.abs(array[n]-array[i] 用来判断是否处于同一对角线
     * 判断同一行没有必要，因为行数一直在递增
     *
     * @param n 第n个皇后
     * @return 是否冲突
     */
    private boolean judge(int n) {

        judgeCount++;
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i]))
                return false;
        }
        return true;
    }

    /**
     * 输出每一种皇后摆放方式的位置
     */
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}

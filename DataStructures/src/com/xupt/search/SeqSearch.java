package com.xupt.search;

/**
 * @author Wnlife
 * @create 2019-11-21 9:20
 * <p>
 * 顺序查找算法
 */
public class SeqSearch {

    public static void main(String[] args) {

        int[] arr = {1, 36, 8, 6, 4, 6, 2, 56, 2, 54, 6};
        int r = seqSearch(arr, 2);
        if (r == -1) {
            System.out.println("没有找到！");
        } else {
            System.out.println("找到的下标为：" + r);
        }
    }

    public static int seqSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key)
                return i;
        }
        return -1;
    }
}

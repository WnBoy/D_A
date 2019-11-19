package com.xupt.sort;

import java.util.Arrays;

/**
 * @author Wnlife
 * @create 2019-10-30 19:02
 * <p>
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
//        int[] arr = {3, 9, -1, 10, 20};

        //排序前
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        //排序前
        System.out.println(Arrays.toString(arr));
    }

    private static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flage;
        for (int i = 0; i < arr.length - 1; i++) {
            flage = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flage = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.printf("第%d趟的排序结果为：", i + 1);
            System.out.println(Arrays.toString(arr));
            if (!flage) break;
        }
    }
}

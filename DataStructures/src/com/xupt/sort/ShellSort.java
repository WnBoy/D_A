package com.xupt.sort;

import java.util.Arrays;

/**
 * @author Wnlife
 * @create 2019-11-19 12:06
 * <p>
 * 希尔排序
 */
public class ShellSort {

    public static void main(String[] args) {

        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println("排序前：" + Arrays.toString(arr));
        shellSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));

    }

    public static void shellSort(int[] arr) {

        //增量gap，并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {

            //对每个分组的元素进行简单插入排序
            for (int i = gap; i < arr.length; i++) {
                int insertVal = arr[i];
                int insertIndex = i - gap;
                if (insertVal < arr[insertIndex]) {

                    while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                        arr[insertIndex + gap] = arr[insertIndex];
                        insertIndex -= gap;
                    }
                    arr[insertIndex + gap] = insertVal;
                }
            }
            System.out.println("增量为"+gap+"的分组排序后的数组："+Arrays.toString(arr));
        }
    }
}
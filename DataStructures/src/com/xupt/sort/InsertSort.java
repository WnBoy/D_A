package com.xupt.sort;

import java.util.Arrays;

/**
 * @author Wnlife
 * @create 2019-11-19 10:26
 * <p>
 * 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {

        int[] arr = {12, 6, 32, 4, 58};
        System.out.println("排序之前：" + Arrays.toString(arr));
        insertSort(arr);
        System.out.println("排序之后：" + Arrays.toString(arr));

    }

    public static void insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1;

            //insertIndex>=0 ：使角标不越界
            //insertVal<arr[insertIndex] ：待插入的数，还没有找到插入位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //说明插入的位置找到了
            if (insertIndex + 1 != i)
                arr[insertIndex + 1] = insertVal;

            System.out.println("第"+i+"数插入后的数组："+Arrays.toString(arr));
        }
    }

}

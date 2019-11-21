package com.xupt.sort;

import java.util.Arrays;

/**
 * @author Wnlife
 * @create 2019-11-19 15:04
 * <p>
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println("排序前：" + Arrays.toString(arr));
        qucikSort(arr,0,arr.length-1);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    public static void qucikSort(int[] arr, int left, int right) {

        if (arr.length <= 0) return;
        if (left >= right) return;
        int l = left;
        int r = right;
        int pivot = arr[l]; //挖坑1：保存基准的值
        while (l < r) {
            while (l < r && arr[r] >= pivot)//坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
                r--;
            arr[l] = arr[r];
            while (l < r && arr[l] <= pivot)//坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
                l++;
            arr[r]=arr[l];
        }
        arr[l]=pivot;//基准值填补到坑3中，准备分治递归快排
        qucikSort(arr,left,l-1);
        qucikSort(arr,l+1,right);
    }
}

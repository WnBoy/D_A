package com.xupt.sort;

import java.util.Arrays;

/**
 * @author Wnlife
 * @create 2019-11-19 17:09
 * <p>
 * 归并排序
 */
public class MergetSort {

    public static void main(String[] args) {

        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println("排序前：" + Arrays.toString(arr));
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("排序后：" + Arrays.toString(arr));

    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {

        if (arr.length <= 0)
            return;
        if (left < right) {
            int mid = (left + right) / 2;//中间索引
            mergeSort(arr, left, mid, temp);//将左边数组进行分解
            mergeSort(arr, mid + 1, right, temp);//将右将边数组进行分解
            merge(arr, left, mid, right, temp);//合并分解后的数组
        }

    }

    //合并的方法
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {

        int i = left;//左边数组的初始索引
        int j = mid + 1;//右边数组的初始索引
        int t = 0;//初始值

        //一: 先把左右两边（有序）的数据按照规则填充到temp数组，
        // 直到左右两边有序数列有一边处理完毕为止
        while (i <= mid && j <= right) {
            //如果左边的有序数列的当前元素小于右边的有序数列的当前元素，
            // 则将左边的数列的当前元素放入到临时数组中
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            }else {
                //如果右边的有序数列的当前元素小于左边的有序数列的当前元素，
                // 则将右边的数列的当前元素放入到临时数组中
                temp[t++]=arr[j++];
            }
        }

        //二: 将左右边数组剩余的元素放入到temp数组中
        while(i<=mid){
            temp[t++] = arr[i++];
        }
        while(j<=right){
            temp[t++] = arr[j++];
        }

        //三: 将temp数组的元素拷贝到arr,注意，并不是每次都拷贝所有
        t=0;
        //第一次合并 tempLeft = 0 , right = 1
        // tempLeft = 2  right = 3 // tL=0 ri=3
        //最后一次 tempLeft = 0  right = 7
        while(left<=right){
            arr[left++]=temp[t++];
        }

    }
}

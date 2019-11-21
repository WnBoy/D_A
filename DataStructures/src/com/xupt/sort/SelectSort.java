package com.xupt.sort;

import java.util.Arrays;

/**
 * @author Wnlife
 * @create 2019-11-19 9:14
 *
 * 选择排序算法
 */
public class SelectSort {

    public static void main(String[] args) {

        int[]arr={65,5,4,35,64,52};
        System.out.println("排序前："+Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后："+Arrays.toString(arr));

    }

    public static void selectSort(int[]arr){

        for (int i = 0; i < arr.length-1; i++) {
            int min=arr[i];
            int minIndex=i;
            for (int j = i+1; j <arr.length ; j++) {
                if(arr[j]<min){
                    min=arr[j];
                    minIndex=j;
                }
            }
            if(minIndex!=i){
                arr[minIndex]=arr[i];
                arr[i]=min;
            }
        }

    }
}

package com.xupt.sort;

import java.util.Arrays;

/**
 * @author Wnlife
 * @create 2019-11-20 10:20
 * <p>
 * 基数排序
 */
public class RadixSort {

    public static void main(String[] args) {

        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println("排序前：" + Arrays.toString(arr));
        radixSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {

        //1.得到数组中最大数的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        int maxLength = (max + "").length();

        //定义一个二维数组表示10个桶，每个桶就是一个一维数组
        //二维数组包含10个一维数组
        int[][] bucket = new int[10][arr.length];

        //使用一个一维数组表示每个桶中实际存放了多少个数据
        //比如：bucketElementCounts[0] , 记录的就是  bucket[0] 桶的放入数据个数
        int[] bucketElementCount = new int[10];

        for (int i = 0, n = 1; i < bucket.length; i++, n *= 10) {
            //针对每个元素的对应位进行对应位的处理，第一次是个位，第二次是十位，第三次是百位
            for (int j = 0; j < arr.length; j++) {
                //取出对应的位元素
                int digitOfElement = arr[j] / n % 10;
                //放入对应的桶中
                bucket[digitOfElement][bucketElementCount[digitOfElement]++] = arr[j];
            }

            //将桶中的元素依次取出，放入原数组中
            int index = 0;
            for (int k = 0; k < bucket.length; k++) {
                //如果当前桶中的实际元素数不为0，循环取出桶中的元素
                if (bucketElementCount[k] != 0) {
                    for (int j = 0; j < bucketElementCount[k]; j++) {
                        //将取出的元素放入到原数组中
                        arr[index++]=bucket[k][j];
                    }
                    //将桶中的元素个数计数器清空
                    bucketElementCount[k]=0;
                }
            }
        }
    }

}

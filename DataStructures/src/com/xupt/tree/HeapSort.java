package com.xupt.tree;

import java.time.Duration;
import java.time.Instant;

/**
 * @author Wnlife
 * @create 2019-12-05 21:36
 * 堆排序
 */
public class HeapSort {

    public static void main(String[] args) {
//        int[] arr = {4, 6, 8, 5, 9, -8, 50, -34, 65};
        int[]arr=new int[8000000];
        for (int i = 0; i <8000000; i++) {
            arr[i]=(int) Math.random()*8000000;
        }
        System.out.println("排序前:");
        Instant now1 = Instant.now();
        heapSort(arr);
        Instant now2 = Instant.now();
//        System.out.println(Arrays.toString(arr));
        System.out.println("排序的时间是："+Duration.between(now1,now2).toMillis());

    }

    /**
     * 堆排序算法
     *
     * @param arr 待排序的数组
     */
    public static void heapSort(int[] arr) {
        int temp = 0;
        //1).将一个无序的数组变为一个堆,根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        /**
         * 2).将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
         * 3).重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，
         * 反复执行调整+交换步骤，直到整个序列有序。
         */
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 将一个数组（二叉树），调整为一个大堆顶
     * 功能：将 以 i 对应的非叶子结点的树调整成大顶堆
     * 举例：int arr[] = {4, 6, 8, 5, 9};  i=1  -> adjustHeap ->  得到 {4, 9, 8, 5, 6}
     * 如果再次调用adjustHeap，传入的是 i = 0 -> 得到 {4, 9, 8, 5, 6} -> {9,6,8,5, 4}
     *
     * @param arr    要调整的数组
     * @param i      表示非叶子结点在数组中索引
     * @param length 表示对多少个元素继续调整， length 是在逐渐的减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//保存当前节点的值
        //k = 2*i+1是当前节点的左子节点
        for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//说明右子节点大于左子节点的值
                k++;
            }
            if (arr[k] > temp) {//如果子节点大于父节点
                arr[i] = arr[k];//将比较大的子节点的值和当前节点的值互换
                i = k;//循环向下比较
            } else {
                break;
            }
        }
        //循环结束后，已经将当前的子树的最大值放在顶部
        arr[i] = temp; // 将temp的值赋值给调整后的位置
    }
}
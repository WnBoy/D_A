package com.xupt.search;

/**
 * @author Wnlife
 * @create 2019-11-21 12:13
 * <p>
 * 插值查找算法
 */
public class InsertSearch {

    public static void main(String[] args) {

        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = insertSearch(arr, 0, arr.length - 1, 10);
        System.out.println(index);

    }

    /**
     * 说明：插值查找算法，也要求数组是有序的
     *
     * @param arr   数组
     * @param left  坐标索引
     * @param right 右边索引
     * @param key   查找的值
     * @return 如果找到，就返回对应的下标，如果没有找到，返回-1
     */
    public static int insertSearch(int[] arr, int left, int right, int key) {

        //注意：findVal < arr[0]  和  findVal > arr[arr.length - 1] 必须需要
        //否则我们得到的 mid 可能越界
        if (left > right || key < arr[left] || key > arr[right]) {
            return -1;
        }
        // 求出mid, 自适应
        int mid = left + (right - left) * (key - arr[left]) / (arr[right] - arr[left]);
        if (key < arr[mid]) {
            return insertSearch(arr, left, mid - 1, key);
        } else if (key > arr[mid]) {
            return insertSearch(arr, mid + 1, right, key);
        } else {
            return mid;
        }
    }

}

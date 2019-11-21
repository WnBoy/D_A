package com.xupt.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wnlife
 * @create 2019-11-21 9:28
 * <p>
 * 二分查找法
 */
public class BinSearch {

    public static void main(String[] args) {

        int[] arr = {1, 8, 10, 89, 1000, 1234};
//        int index = binarySearch1(arr, 0, arr.length-1, 89);
        int index = binarySearch2(arr, 89);
        System.out.println(index);
    }

    /**
     * 递归版的二分法查找
     *
     * @param arr   要查找的数组
     * @param left  数组左边的值
     * @param right 数组右边的值
     * @param key   要查找的元素
     * @return 查找到的值
     */
    public static int binarySearch1(int[] arr, int left, int right, int key) {

        //表示找不到
        if (left > right) {
            return -1;
        }
        //递归查找
        int mid = (left + right) / 2;
        if (arr[mid] > key) {
            return binarySearch1(arr, left, mid - 1, key);
        } else if (arr[mid] < key) {
            return binarySearch1(arr, mid + 1, right, key);
        } else {
            return mid;
        }
    }

    /**
     * 循环版的二分法查找
     *
     * @param arr
     * @param key
     * @return
     */
    public static int binarySearch2(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr[mid] > key) {
                right = mid - 1;
            } else if (arr[mid] < key) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /*
     * 完成一个课后思考题:
     *
     * 课后思考题： {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
     *
     * 思路分析
     * 1. 在找到mid 索引值，不要马上返回
     * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 4. 将Arraylist返回
     */
    public static List<Integer> binarySearch3(int[] arr, int left, int right, int key) {

        //没找到
        if (left > right)
            return new ArrayList<Integer>();
        //递归查找
        int mid = (left + right) >> 1;
        if (key < arr[mid]) {
            return binarySearch3(arr, left, mid - 1, key);
        } else if (key > arr[mid]) {
            return binarySearch3(arr, mid + 1, right, key);
        } else {
            // 1. 在找到mid 索引值，不要马上返回
            List<Integer> list = new ArrayList<Integer>();
            // 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            int temp = mid - 1;
            while (true) {
                if (temp < left || arr[temp] != key) {
                    break;
                }
                list.add(temp);
            }
            list.add(mid);
            // 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            temp = mid + 1;
            while (true) {
                if (temp > right || arr[temp] != key) {
                    break;
                }
                list.add(temp);
            }
            // 4. 将Arraylist返回
            return list;
        }

    }

}

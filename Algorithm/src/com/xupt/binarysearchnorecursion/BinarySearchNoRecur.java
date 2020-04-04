package com.xupt.binarysearchnorecursion;

/**
 * @author Wnlife
 * @create 2020-02-01 19:13
 * <p>
 * 二分查找算法，非递归的形式
 */
public class BinarySearchNoRecur {

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearchNoRecur(arr, 100);
        System.out.println("index=" + index);
    }

    public static int binarySearchNoRecur(int[] arr, int target) {
        int first = 0;
        int end = arr.length - 1;
        while (first <= end) {
            int mid = (first + end) >> 1;
            if (arr[mid] > target) {
                end = mid - 1;
            } else if (arr[mid] < target) {
                first = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

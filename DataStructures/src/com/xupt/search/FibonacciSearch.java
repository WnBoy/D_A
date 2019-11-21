package com.xupt.search;

import java.util.Arrays;

/**
 * @author Wnlife
 * @create 2019-11-21 16:12
 * <p>
 * 斐波那契（黄金分割查找）查找f
 */
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {

        int[] arr = {1, 8, 10, 89, 1000, 1234};

        System.out.println("index=" + fibonacciSearch(arr, 1234));// 0
    }

    /**
     * 创建斐波那契数列
     *
     * @return 斐波那契数列
     */
    public static int[] fib() {
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    /**
     * 使用非递归的方式编写斐波那契查找算法
     *
     * @param arr 查找的数组
     * @param key 要查找的值
     * @return 查找到的数组索引，找到返回角标，找不到返回-1
     */
    public static int fibonacciSearch(int[] arr, int key) {

        int left = 0;
        int right = arr.length - 1;
        int k = 0;//表示斐波那契数列的下标
        int mid = 0;
        int f[] = fib();//获取的斐波那契数列

        //计算斐波那契数列的下标
        while (arr.length > f[k] - 1) {
            k++;
        }
        //原数组的长度n不一定刚好等于f[k]-1，所以需要将原来的顺序表长度n增加至f[k]-1
        //因此需要重新创建一个数组，将原数组分值复制过去
        int[] temp = Arrays.copyOf(arr, f[k] - 1);
        //新增的位置（从n+1到f[k]-1位置），都赋为n位置的值即可
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }

        //查找对应值的角标
        while (left <= right) {
            //由斐波那契数列 F[k]=F[k-1]+F[k-2] 的性质，可以得到 （F[k]-1）=（F[k-1]-1）+（F[k-2]-1）+1 。
            // 该式说明：只要顺序表的长度为F[k]-1，则可以将该表分成长度为F[k-1]-1和F[k-2]-1的两段，即如上图所示。从而中间位置为mid=low+F(k-1)-1
            //现在数组的长度已经变为f[k]-1，则可以使用上述理论
            mid = left + f[k - 1] - 1;
            if (key < temp[mid]) {//需要在数组的左边查找，即：在（F[k-1]-1）这边找
                right = mid - 1;
                //对应的斐波那契数列的为F[k-1]，k需要减1
                k -= 1;
            } else if (key > temp[mid]) {//需要在数组的右边查找，即：在（F[k-1]-1）这边找
                left = mid + 1;
                //对应的斐波那契数列的为F[k-2]，k需要减2
                k -= 2;
            } else {//找到对应的元素
                //需要判断返回哪个下标，因为目前使用的是增长后的临时数组
                if (mid <= right) {
                    return mid;
                } else {
                    return right;
                }
            }
        }
        return -1;
    }

}

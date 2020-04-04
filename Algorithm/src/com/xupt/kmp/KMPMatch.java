package com.xupt.kmp;

import java.util.Arrays;

/**
 * @author Wnlife
 * @create 2020-02-02 21:52
 * <p>
 * KMP字符串匹配算法
 */
public class KMPMatch {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        //String str2 = "BBC";
        //[0, 1, 2, 0]
        int[] next = kmpNext("ABCDABD");

        System.out.println("next=" + Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        // 15了
        System.out.println("index=" + index);

    }

    public static int kmpSearch(String str1, String str2, int[] next) {
        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //需要处理 str1.charAt(i) ！= str2.charAt(j), 去调整j的大小
            //KMP算法核心点, 可以验证...
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取到一个字符串(子串) 的部分匹配值表
     *
     * @param dest 子串
     * @return 部分匹配值表
     */
    public static int[] kmpNext(String dest) {
        //创建一个next 数组保存部分匹配值
        int[] next = new int[dest.length()];
        //如果字符串是长度为1 部分匹配值就是0
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j) ，我们需要从next[j-1]获取新的j
            //直到我们发现 有  dest.charAt(i) == dest.charAt(j)成立才退出
            //这时kmp算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            //当dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值就是+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}

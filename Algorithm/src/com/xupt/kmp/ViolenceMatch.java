package com.xupt.kmp;

/**
 * @author Wnlife
 * @create 2020-02-02 21:38
 *
 * 字符串暴力匹配算法
 */
public class ViolenceMatch {

    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int r = volianceMatch(str1, str2);
        System.out.println(r);
    }

    public static int volianceMatch(String str1,String str2){
        char[]s1=str1.toCharArray();
        char[]s2=str2.toCharArray();

        int str1Len=str1.length();
        int str2Len=str2.length();
        // i索引指向s1
        int i=0;
        // j索引指向s2
        int j=0;
        // 保证匹配时，不越界
        while (i<str1Len&&j<str2Len){
            //匹配成功
            if(s1[i]==s2[j]){
                i++;
                j++;
            }else {
                //匹配不成功，如果失配（即str1[i]! = str2[j]），令i = i - (j - 1)，j = 0。
                i=i-(j-1);
                j=0;
            }
        }
        //判断是否匹配成功
        if(j==str2Len){
            return i-j;
        }else {
            return -1;
        }
    }
}

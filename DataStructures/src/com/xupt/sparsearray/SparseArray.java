package com.xupt.sparsearray;

import java.util.Arrays;

/**
 * @author Wnlife
 * @create 2019-10-17 15:00
 *
 *  二维数组 -> 稀疏数组
 *  稀疏数组 -> 二维数组
 *
 */
public class SparseArray {

    public static void main(String[] args) {

        //创建一个原始的二维数组11*11
        //0：表示没有棋子，1表示黑子，2表示蓝子
        int[][] chessArr1=new int[11][11];
        chessArr1[1][2]=1;
        chessArr1[2][3]=2;
        chessArr1[4][5]=2;

        //打印原始二维数组
        System.out.println("二维数组~~");
        Arrays.stream(chessArr1).forEach((n)->{
            Arrays.stream(n).forEach((m)->System.out.printf("%d\t",m));
            System.out.println();
        });

        /**
         * 1.将二维数组转成稀疏数组
         *   ①遍历原始的二维数组，计算二维数组的有效数据个数sum
         *   ②根据得到的sum创建稀疏数组sparseArr int[sum+1][3]
         *   ③将二维数组的有效数据存储到稀疏数组
         */

        //①遍历原始的二维数组，计算二维数组的有效数据个数sum

        int sum=0;
        for (int[] n : chessArr1) {
            long count = Arrays.stream(n).filter((m) -> {
                if (m != 0)
                    return true;
                else
                    return false;
            }).count();
            sum += (int)count;
        }

        //②根据得到的sum创建稀疏数组sparseArr int[sum+1][3]
        int[][] sparseArr = new int[sum + 1][3];

        //③将二维数组的有效数据存储到稀疏数组
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        int k=0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                if(chessArr1[i][j]!=0){
                    k++;
                    sparseArr[k][0]=i;
                    sparseArr[k][1]=j;
                    sparseArr[k][2]=chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println("稀疏数组~~");
        Arrays.stream(sparseArr).forEach((n)->{
            Arrays.stream(n).forEach((m)->System.out.printf("%d\t",m));
            System.out.println();
        });


        /**
         * 2.稀疏数组恢复为原来的二维数组
         *  ①先读取数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的chessArr2=new int[11][11];
         *  ②在读取稀疏数组的后面几行数组，并幅值给原始的二维数组即可。
         */

        //①先读取数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的chessArr2=new int[11][11];
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }

        //数组恢复后的二维数组
        System.out.println("数组恢复后的二维数组~~");
        Arrays.stream(chessArr2).forEach((n)->{
            Arrays.stream(n).forEach((m)->System.out.printf("%d\t",m));
            System.out.println();
        });
    }
}

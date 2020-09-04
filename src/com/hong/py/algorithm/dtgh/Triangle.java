package com.hong.py.algorithm.dtgh;

import java.util.Arrays;

/**
 * author: hongpy
 * create time: 2020-06-29 00:31
 * description:
 * life for code
 */
public class Triangle {

    public static void main(String[] args) {
        Triangle triangle = new Triangle();
        triangle.triangle(new int[][]{
                {2, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {3,4, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {6,5, 7, Integer.MAX_VALUE},
                {4,8, 3, 1}});
    }


    public void triangle(int[][] datas) {
        int rows = datas.length;
        int[][] fill = new int[rows][datas[0].length];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(fill[i], Integer.MAX_VALUE);
        }

        fill[0][0]=datas[0][0];
        for (int i = 1; i < rows; i++) { //第几行
            //每一行上的元素遍历
            for (int j = 0; j < datas[i].length; j++) {
                if(datas[i][j]==Integer.MAX_VALUE) continue;
                if (j == 0&&fill[i - 1][j]!=Integer.MAX_VALUE) {
                    fill[i][j] = Math.min(fill[i][j], fill[i - 1][j] + datas[i][j]);
                } else {
                    if(fill[i - 1][j-1]!=Integer.MAX_VALUE)
                      fill[i][j] = Math.min(fill[i][j], fill[i - 1][j-1] + datas[i][j]);
                    if(fill[i - 1][j]!=Integer.MAX_VALUE)
                      fill[i][j] = Math.min(fill[i][j], fill[i - 1][j] + datas[i][j]);
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            System.out.println(Arrays.toString(fill[i]));
        }
    }
}

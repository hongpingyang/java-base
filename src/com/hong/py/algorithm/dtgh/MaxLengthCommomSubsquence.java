package com.hong.py.algorithm.dtgh;

import java.util.Arrays;

/**
 * author: hongpy
 * create time: 2020-07-01 12:03
 * description:
 * life for code
 */
public class MaxLengthCommomSubsquence {

    public static void main(String[] args) {
        MaxLengthCommomSubsquence max = new MaxLengthCommomSubsquence();
        max.maxLengthCommomSubsquence("1A2C3D4B56","B1D23CA45B6A");
    }


    public void maxLengthCommomSubsquence(String s1, String s2) {

        int m=s1.length();
        int n = s2.length();
        int[][] f=new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean equals = s1.substring(i, i + 1).equals(s2.substring(j, j + 1));
                if (i == 0 || j == 0) {
                    if (i == 0 && j == 0) {
                        if (equals) {
                            f[i][j] = 1;//Math.max(1, f[i][j-1]);
                        } else {
                            f[i][j] = 0;
                        }
                    } else if (i == 0&&j>=1){
                        if (equals) {
                            f[i][j] = Math.max(1, f[i][j - 1]);
                        } else {
                            f[i][j] = Math.max(0, f[i][j - 1]);
                        }
                    }
                    else if (j == 0&&i>=1){
                        if (equals) {
                            f[i][j] = Math.max(1, f[i - 1][j]);
                        } else {
                            f[i][j] = Math.max(0, f[i - 1][j]);
                        }
                    }
                }
                else {
                    int up = f[i][j-1];
                    int left = f[i - 1][j];
                    int temp = f[i - 1][j - 1];
                    if (equals) {
                        f[i][j] = Math.max(Math.max(up, left), temp + 1);
                    } else {
                        f[i][j] = Math.max(Math.max(up, left), temp);
                    }
                }
            }
        }


        for (int i = 0; i < m; i++) {
            System.out.println(Arrays.toString(f[i]));
        }
        System.out.println("最长公共子序列:"+f[m-1][n-1]);

    }
}

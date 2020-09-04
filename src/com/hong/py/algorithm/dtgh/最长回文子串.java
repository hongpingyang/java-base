package com.hong.py.algorithm.dtgh;

import java.util.Arrays;

/**
 * author: hongpy
 * create time: 2020-07-01 14:56
 * description:
 * life for code
 */
public class 最长回文子串 {

    public static void main(String[] args) {
        最长回文子串 m = new 最长回文子串();
        m.least("aabaaXaabaa");
    }


    public void least(String str) {
        int n=str.length();
        boolean[][] f = new boolean[n][n];
        //f[m][n] 表示从m到n是否是回文子串
        int maxbeginindex=0;
        int maxendindex=0;
        for (int i = n-1; i >=0; i--) {
            for (int j = n-1; j >=i; j--) { //要从大向下遍历
                if (i == j) { //一个字符或者2个字符都是回文
                    f[i][j] = true;

                    if (j - i >maxendindex - maxbeginindex) {
                        maxbeginindex=i;
                        maxendindex=j;
                    }

                } else if (i + 1 == j) {
                    if (str.substring(i, i + 1).equals(str.substring(i+1, i + 2))) {
                        f[i][j] = true;

                        if (j - i >maxendindex - maxbeginindex) {
                            maxbeginindex=i;
                            maxendindex=j;
                        }
                    }
                } else {
                    if (str.substring(i, i + 1).equals(str.substring(j, j + 1))) {
                        if (f[i + 1][j - 1]) {
                            f[i][j] = true;

                            if (j - i >maxendindex - maxbeginindex) {
                                maxbeginindex=i;
                                maxendindex=j;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(f[i]));
        }
        System.out.println("最大回文子串为："+str.substring(maxbeginindex,maxendindex+1));
    }


}

package com.hong.py.algorithm.dtgh;

import java.util.Arrays;
import java.util.Comparator;

/**
 * author: hongpy
 * create time: 2020-06-29 09:25
 * description:
 * life for code
 */
public class IntegerBreak {

    private static Integer[] memo;

    /**
     * 求一个数分割后的 最大乘积  至少要分成两个数
     * @param args
     */
    public static void main(String[] args) {
        IntegerBreak integerBreak = new IntegerBreak();
        memo = new Integer[20+1];
        int i = integerBreak.integerBreak(10);
        System.out.println(i);

        int i1 = integerBreak.integerBreak1(20);
        System.out.println(i1);

        Arrays.sort(memo, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                return 0;
            }
        });
    }

    /**
     * 递归和记忆法
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        if (n == 1) {
            return 1;
        }

        int maxresult=0;
        if (memo[n] != 0)
            return memo[n];
        for (int i = 1; i <= n - 1; i++) { //对n开始分割的情况遍历
            maxresult=Math.max(Math.max(maxresult,i*(n-i)),i*integerBreak(n-i));
        }
        memo[n]=maxresult;
        return maxresult;
    }


    /**
     * 动态规划
     * @param n
     * @return
     */
    public int integerBreak1(int n) {

        if (n == 1) {
            return 1;
        }

        for (int i = 2; i <=n; i++) { //从 i=2 3....n开始
            for (int j = 1; j <i ; j++) { //对开始分割的情况遍历

                memo[i] = Math.max(Math.max(memo[i], j * (i -j) ), j * memo[i -j]);
            }
        }
        return memo[n];
    }
}

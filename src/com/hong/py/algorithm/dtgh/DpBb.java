package com.hong.py.algorithm.dtgh;

/**
 * author: hongpy
 * create time: 2020-06-09 15:37
 * description:
 * life for code
 * 动态规划 背包
 */
public class DpBb {

    public static void main(String[] args) {
        int[] weight = {1, 2, 4};
        //int[] weight2 = {1, 2, 3};
        int[] value = {6, 10, 12};
        dp(weight,value,5);
        dp1(weight,value,5);

        int[] weight1 = {1, 2, 3};
        int[] value1 = {6, 13, 18};
        dp2(weight1,value1,5);

        int[] numlimit = {2, 1, 2};
        dp3(weight1,value1,numlimit,5);

        //硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法
        int[] coins = {1, 5, 10, 25};
        toChange(coins, 7);
    }


    /**
     * 0，1背包
     * @param weight
     * @param value
     * @param total
     */
    public static void dp(int[] weight, int value[],int total) {
       int w=weight.length;
       int v=value.length;
       int[][] dp=new int[v+1][total+1];
        for (int i = 1; i <=v; i++) { //每加入一个物品
            for (int j = 1; j <=total; j++) { //
                if (weight[i-1] <= j) { //能放得下 选择最大值
                    dp[i][j]=Math.max(value[i-1]+dp[i-1][j-weight[i-1]],dp[i-1][j]);
                } else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        System.out.println(dp[v][total]);
    }

    /**
     *0，1背包 的一维数组
     * @param weight
     * @param value
     * @param total
     */
    public static void dp1(int[] weight, int value[], int total) {
        int w=weight.length;
        int v=value.length;
        int[] dp=new int[total+1];
        for (int i = 1; i <=v; i++) { //每加入一个物品
            for (int j = total; j >=weight[i-1]; j--) { //从后往前计算 不能让计算过的值再影响计算。这就是与完全背包的区别
                    dp[j]=Math.max(value[i-1]+dp[j-weight[i-1]],dp[j]);
              }
            }
        System.out.println(dp[total]);
    }

    /**
     * 这个是求解可重复放的情况下背包的能装的最大价值 完全背包
     * @param weight
     * @param value
     * @param total
     */
    public static void dp2(int[] weight, int value[], int total) {
        int w=weight.length;
        int v=value.length;
        int[] dp=new int[total+1];
        for (int i = 1; i <=v; i++) { //每加入一个物品
            for (int j = 1; j <=total; j++) { //
                if (weight[i-1] <= j) { //能放得下 选择最大值。每次计算依赖前面的结果，
                    dp[j]=Math.max(value[i-1]+dp[j-weight[i-1]],dp[j]);
                } else {
                    dp[j]=dp[j]; //用前面的值 其实不这异步可以省略
                }
            }
        }
        System.out.println(dp[total]);
    }

    /**
     * 这个是求解可重复放的情况下背包的能装的最大价值，但物品的数量有限制，多重背包,就是0,1背包添加了一个数量限制判断
     * @param weight
     * @param value
     * @param total
     */
    public static void dp3(int[] weight, int value[],int[] numlimit, int total) {
        int w=weight.length;
        int v=value.length;
        int[] dp=new int[total+1];
        for (int i = 1; i <=v; i++) { //每加入一个物品
            for (int j = total; j >0; j--) { //

                for (int k = 1; k <= numlimit[i - 1]&&j>=weight[i - 1]*k; k++) { //物品的个数的遍历
                    dp[j] = Math.max(dp[j], dp[j - weight[i - 1] * k] + value[i - 1] * k);
                }
                //if (i == 1) {
                //System.out.println("===========");
                //System.out.println(j);
                //System.out.println(weight[i - 1]);
                //System.out.println(dp[j]);
                //}
            }
        }
        System.out.println(dp[total]);
    }

    public static void toChange(int[] coins, int money) {
        System.out.println("=====toChange======");
        int n=coins.length;
        int dp[] = new int[money+1];
        dp[0]=1;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <=money; j++) {
                if (j >= coins[i - 1]) {
                    //dp[j]                     +左边表示 不选择的情况
                    //dp[j - coins[i - 1]]      -右边表示 选择的情况
                    dp[j] = dp[j]  +   dp[j - coins[i - 1]];
                } else {
                    dp[j] = dp[j];//这一步其实是多余的
                }
            }
        }
        System.out.println(dp[money]);
    }



}

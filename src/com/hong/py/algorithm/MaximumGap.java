package com.hong.py.algorithm;

import java.util.Arrays;

/**
 * author: hongpy
 * create time: 2020-06-19 10:54
 * description:
 * life for code
 *
 * 计算一个未排序数组中排序后相邻元素的最大差值
 */
public class MaximumGap {

    public static void main(String[] args) {
        MaximumGap maximumGap = new MaximumGap();
        System.out.println(maximumGap.maximumGap(new int[]{100, 5, 4, 0, 9,}));
    }


    public int maximumGap(int[] datas) {
        if (datas.length < 2) {
            return 0;
        }
        Arrays.sort(datas);
        int maxGap=0;
        for (int i = 1; i < datas.length; i++) {
            int value = Math.abs(datas[i] - datas[i - 1]);
            if (value > maxGap) {
                maxGap=value;
            }
        }
        return maxGap;
    }

}

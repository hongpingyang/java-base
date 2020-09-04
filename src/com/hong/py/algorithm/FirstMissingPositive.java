package com.hong.py.algorithm;

import java.util.Arrays;

/**
 * author: hongpy
 * create time: 2020-06-18 20:20
 * description:
 * life for code
 */
public class FirstMissingPositive {


    /**
     * 找到第一个缺失的正整数
     * @param args
     */
    public static void main(String[] args) {
        FirstMissingPositive firstMissingPositive = new FirstMissingPositive();

        firstMissingPositive.firstMissingPositive(new int[]{1,2,0});
        firstMissingPositive.firstMissingPositive(new int[]{3,4,-1,1});
    }


    public void firstMissingPositive(int[] datas) {

        Arrays.sort(datas);
        int[] bucket = new int[datas[datas.length-1]+1]; //找到最大的元素那个，生成数组，如果很大，这种方式就不是很好
        for (int i = 0; i < datas.length; i++) {
            if (datas[i] >= 0) {
                bucket[datas[i]]=1; //1表示这个位置上由数 ，0表示没有
            }
        }

        boolean hasresult=false;
        for (int i = 1; i < bucket.length; i++) {
            if (bucket[i] == 0) {
                hasresult = true;
                System.out.println(i);
            }
        }

        if (!hasresult) {
            System.out.println(bucket.length);
        }
    }
}

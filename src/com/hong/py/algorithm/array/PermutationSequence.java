package com.hong.py.algorithm.array;

import java.util.Arrays;

/**
 * author: hongpy
 * create time: 2020-06-19 09:26
 * description:
 * life for code
 * //字典排序找出第几个字典排序
 */
public class PermutationSequence {

    public static void main(String[] args) {
        PermutationSequence permutationSequence = new PermutationSequence();
        permutationSequence.permutationSequence(3, 5);
    }

    public void permutationSequence(int n, int k) {

        int[] datas=new int[n];
        for (int i = 0; i < n; i++) {
            datas[i]=i+1;
        }

        int[] next = next(datas, n, 0, k);
        System.out.println(Arrays.toString(next));
    }

    public int[] next(int[] datas, int n,int times,int k) {

        if (times == k) {
            return datas;
        }

        int first=-1;
        int select=0;

        for (int i = n-1; i >= 1; i--) {
            if (datas[i] > datas[i - 1]) {
                first=i-1;
                break;
            }
        }

        //找到了
        if (first != -1) {
            //找到第一个比first大的
            for (int i = n-1; i >= 1; i--) {
                if (datas[i] > datas[first]) {
                    select=i;
                    break;
                }
            }

            int temp = datas[first];
            datas[first] = datas[select];
            datas[select] = temp;
        }

        //first后面的循序反转
        for (int i = first+1; i <n ; i++) {
            if (i < n - (i-first-1) -1) {
                swap(i, n - (i-first-1) -1, datas);
            }
        }

        return next(datas, n,times+1,k);
    }

    public void swap(int begin, int end, int[] datas) {
        int temp = datas[begin];
        datas[begin] = datas[end];
        datas[end] = temp;

    }
}

package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-08 17:05
 * description:
 * life for code
 */
public class MaopaoSort {

    public static void main(String[] args) {
        int data[]=new int[]{10,28,80,90,50,16,100,10};
        maopaoSort(data,  8);
        for (int i = 0; i < 8; i++) {
            System.out.println(data[i]);
        }
    }

    public static void maopaoSort(int[] data, int length) {

        for (int i = 0; i < length - 1; i++) {//冒泡的次数，没完成一次冒泡，就确定一个元素的位置 这个位置就是最后的位置
            for (int j = 0; j < length - 1 - i; j++) {
                if (data[j] > data[j+1]) {
                    int temp=data[j+1];
                    data[j+1]=data[j];
                    data[j] = temp;
                }
            }
        }
    }
}

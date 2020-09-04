package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-08 17:10
 * description:
 * life for code
 */
public class SelectSort {

    public static void main(String[] args) {
        int data[]=new int[]{10,28,80,90,50,16,100,10};
        selectSort(data,  8);
        for (int i = 0; i < 8; i++) {
            System.out.println(data[i]);
        }
    }

    public static void selectSort(int[] data, int length) {

        for (int i = 0; i < length; i++) {
            int min=data[i];
            int minindex=i;
            //从后面选择比data[i]小的
            for (int j = i; j < length; j++) {
                if (data[j] < min) {
                    min = data[j];
                    minindex=j;
                }
            }

            if (min < data[i]) {
                int temp = data[minindex];
                data[minindex]=data[i];
                data[i]=temp;
            }
        }
    }
}

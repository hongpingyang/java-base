package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-08 17:20
 * description:
 * life for code
 */
public class InsertSort {
    public static void main(String[] args) {
        int data[]=new int[]{10,28,80,90,50,16,100,10};
        insertSort(data,  8);
        for (int i = 0; i < 8; i++) {
            System.out.println(data[i]);
        }
    }


    public static void insertSort(int[] data, int length) {

        for (int i = 1; i < length; i++) {

            int j=i-1;
            int value = data[i];
            for (; j >=0; j--) {
                //与前面的比较，比前面的小，前面的数据往后挪
                if (value < data[j]) {
                    data[j+1]=data[j];
                } else {
                    break;
                }
            }
            data[j + 1] = value;
        }
    }
}

package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-08 16:14
 * description:
 * life for code
 */
public class QuickSort {

    public static void main(String[] args) {
        int data[]=new int[]{10,28,80,90,50,16,100,10};
        quickSort(data, 0, 7);
        for (int i = 0; i < 8; i++) {
            //System.out.println(data[i]);
        }
    }

    public static void quickSort(int[] data, int left, int right) {
        int base = data[left];
        int ll = left;
        int rr = right;

        while (ll < rr) { //如果相等了， 说明 这个base元素的位置已经确定了
            System.out.println("==========:"+base);
            //从后面去找比这个base元素小的元素
            while (ll < rr && base <= data[rr]) {
                rr--;
            }
            System.out.println(rr);
            //找到了 交换位置
            if (ll < rr) {
                int temp = data[rr];
                data[rr] = base;
                data[ll] = temp;
            }
            //从前面去找比这个base元素大的元素
            while (ll < rr && base >= data[ll]) {
                ll++;
            }
            System.out.println(ll);
            //找到了 交换位置
            if (ll < rr) {
                int temp = data[ll];
                data[ll] = base;
                data[rr] = temp;
            }
        }

        System.out.println("==========");
        for (int i = 0; i < 8; i++) {
            System.out.println(data[i]);
        }
        if(ll>1)
           quickSort(data, 0, ll - 1);
        if(rr<right-1)
           quickSort(data, rr + 1, right);

    }
}

package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-08 13:59
 * description:
 * life for code
 *
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int data[]=new int[]{9,5,6,8,0,3,7,1};
        mergeSort(data, 0, 7);
        for (int i = 0; i < 7; i++) {
            System.out.println(data[i]);
        }
    }


    public static void mergeSort(int[] data, int left, int right) {
        if (left < right) {
            //先拆
            int mid = (left + right) / 2;
            mergeSort(data, left, mid);
            mergeSort(data, mid + 1, right);

            //拆到不能拆了，就要合并了
            mergeData(data, left, mid, right);
        }
    }

    //归并
    public static void mergeData(int[] data, int left, int mid, int right) {
        int[] temp = new int[right+1];
        int ponit1=left;
        int point2=mid+1;
        int location=0;

        //比较插入位置
        while (ponit1 <= mid && point2 <= right) {
            if (data[ponit1] < data[point2]) {
                temp[location]=data[ponit1];
                ponit1++;
                location++;
            }else {
                temp[location]=data[point2];
                point2++;
                location++;
            }
        }

        //填充剩下的
        while (ponit1 <= mid) {
            temp[location]=data[ponit1];
            ponit1++;
            location++;
        }

        while (point2 <= right) {
            temp[location]=data[point2];
            point2++;
            location++;
        }

        System.out.println("================");
        for (int i = 0; i < location; i++) {
            System.out.println(i+":"+temp[i]);
            data[left+i] = temp[i];
        }
    }
}

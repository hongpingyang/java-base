package com.hong.py.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * author: hongpy
 * create time: 2020-06-17 11:28
 * description:
 * life for code
 */
public class RemoveDuplicatesfromSortedArray {

    public static void main(String[] args) {
        RemoveDuplicatesfromSortedArray removeDuplicatesfromSortedArray = new RemoveDuplicatesfromSortedArray();
        int[] datas={1,1,2,2,2,3,4,5,7,7};
        removeDuplicatesfromSortedArray.removeDuplicatesfromSortedArray(datas);

        int[] datas1={1,1,2,2,2,3,4,5,7,7};
        removeDuplicatesfromSortedArray.removeDuplicatesfromSortedArrayForTwice(datas1);
    }

    /**
     * 已经排序号的数组，存在重复的需要去重，例如数组位[1,1,2] ,不重复的元素数量位2，输出位[1,2]
     * @param datas
     */
    public void removeDuplicatesfromSortedArray(int[] datas) {

        int count=1;
        int length=datas.length;
        for (int i = 1; i < length; i++) {
            if (datas[i] != datas[i - 1]) {
                datas[count]=datas[i];
                count++;
            } else {
                //System.out.println(Arrays.toString(datas));
            }
        }
        System.out.println(count);
        System.out.println(Arrays.toString(datas));
    }

    /**
     * 已经排序号的数组，存在重复超过2次的需要去重，例如数组位[1,1,1,2,2,3] ,不重复的元素数量位5，输出位[1,1,2,2,3]
     * @param datas
     */
    public void removeDuplicatesfromSortedArrayForTwice(int[] datas) {

        int count=1;
        int length=datas.length;
        int valueCount=0;
        for (int i = 1; i < length; i++) {
            if (datas[i] != datas[i - 1]) {
                datas[count]=datas[i];
                count++;
                valueCount=1;
            } else {
                if (valueCount <= 1) {
                    datas[count]=datas[i];
                    count++;
                }
                valueCount++;
            }
        }
        System.out.println(count);
        System.out.println(Arrays.toString(datas));
    }
}

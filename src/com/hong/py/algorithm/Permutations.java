package com.hong.py.algorithm;

import java.util.Arrays;

/**
 * author: hongpy
 * create time: 2020-06-26 00:17
 * description:
 * life for code
 */
public class Permutations {

    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        permutations.next(new int[]{1,2,3},new int[]{3,2,1});
        permutations.next(new int[]{1,1,2},new int[]{2,1,1});
    }


    public void permutations(int[] datas) {
        next(datas, datas);
    }

    public void next(int[] datas,int[] firstDatas) {

        int length = datas.length;

        System.out.println(Arrays.toString(datas));

        boolean iscircle=true;
        for (int i = 0; i < length; i++) {
            if (datas[i] != firstDatas[i]) {
                iscircle = false;
            }
        }

        if (iscircle) {
            return;
        }

        int first=-1;
        int select=-1;
        //找到第一个降序的
        for (int i = length-1; i >0 ; i--) {
            if (datas[i] > datas[i - 1]) {
                first=i-1;
                break;
            }
        }
        //找到第一个比它大的
        if (first != -1) {
            for (int i = length-1; i >=0 ; i--) {
                if (datas[i] > datas[first]) {
                    select=i;
                    break;
                }
            }

            //两两交换位置
            int temp = datas[first];
            datas[first] = datas[select];
            datas[select] = temp;
        }

        //从first后面的元素两两交换位置

        for (int i = first + 1; i < length; i++) {
            if(length-1-(i-first-1)>i)
             swap(datas,i,length-1-(i-first-1));
        }

        next(datas,firstDatas);
    }

    public void swap(int[] datas, int loc1, int loc2) {
        int temp = datas[loc1];
        datas[loc1] = datas[loc2];
        datas[loc2]=temp;
    }
}

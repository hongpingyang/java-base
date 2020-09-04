package com.hong.py.algorithm.array;

import java.util.Arrays;

/**
 * author: hongpy
 * create time: 2020-06-18 10:28
 * description:
 * life for code
 */
public class MoveZeros {

    public static void main(String[] args) {

        //把数组中的0挪到数组的最后面，且非0的数的排序不能改变，且不用使用复制另一个数组
        MoveZeros moveZeros = new MoveZeros();
        moveZeros.moveZeros(new int[]{0,1,0,3,12});
    }


    public void moveZeros(int[] datas) {

        int index=0;
        //把非0的元素先插入到相应的位置
        for (int i = 0; i < datas.length; i++) {
            if (datas[i] != 0) {
                datas[index]=datas[i];
                index++;
            }
        }

        //后面的补零
        for (int i = index; i < datas.length; i++) {
            datas[i]=0;
        }
        System.out.println(Arrays.toString(datas));
    }
}

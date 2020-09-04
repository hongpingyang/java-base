package com.hong.py.algorithm.array;

import java.util.Arrays;

/**
 * author: hongpy
 * create time: 2020-06-18 11:03
 * description:
 * life for code
 */
public class NextPermutation {

    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();
        np.nextPermutation(new int[]{1,2,7,4,3,1});
        np.nextPermutation(new int[]{1,2,3});
        np.nextPermutation(new int[]{3,2,1});
        np.nextPermutation(new int[]{1,1,5});
    }

    /**
     * 查找下一个字典序
     * @param datas
     */
    public void nextPermutation(int[] datas) {

        int first=-1;
        int select=-1;

        // 例如 1 3 2 找到 1 first=0
        //从后往前找第一个降序的数字
        for (int i = datas.length - 1; i >0; i--) {
            if (datas[i] > datas[i - 1]) {
                first=i-1;
                break;
            }
        }

        if (first != -1) {
            // 例如 1 3 2 找到 2  select=2
            for (int i = datas.length - 1; i > first; i--) {
                if (datas[i] > datas[first]) {
                    select = i;
                    break;
                }
            }
            //交换2个位置
            // 例如 1 3 2 -> 2 3 1
            int temp = datas[first];
            datas[first] = datas[select];
            datas[select] = temp;
        }

        //反转后面的降序变为升序
        //前后两两交换位置    2 3 1  -> 2 1 3
        for (int i = first+1; i <datas.length ; i++) {
            if(i<datas.length-1-(i-first-1))
               swap(i,datas.length-1-(i-first-1),datas);
        }

        System.out.println(Arrays.toString(datas));
    }

    public void swap(int begin, int end, int[] datas) {
        int temp = datas[begin];
        datas[begin] = datas[end];
        datas[end] = temp;
    }
}

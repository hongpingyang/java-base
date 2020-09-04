package com.hong.py.algorithm.array;

import java.util.HashMap;
import java.util.HashSet;

/**
 * author: hongpy
 * create time: 2020-06-18 09:13
 * description:
 * life for code
 */
public class TwoSum {

    public static void main(String[] args) {
        TwoSum sum = new TwoSum();
        //sum.TwoSum(new int[]{2,5,7,4,11,15},9);
        sum.TwoSum2(new int[]{2,5,7,4,11,15},9);
    }


    public void TwoSum(int[] datas, int target) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < datas.length; i++) {
            set.add(datas[i]);
        }

        for (int i = 1; i < target/2+1; i++) {

            if (set.contains(i) && set.contains(target - i)) {
                //存在这种
                int index1=0;
                int index2=0;
                boolean flag1=false;
                boolean flag2=false;
                for (int j = 0; j < datas.length; j++) {

                    if (datas[j] == i) {
                        index1=j+1;
                        flag1=true;
                    }
                    if (datas[j] == target - i) {
                        index2=j+1;
                        flag2=true;
                    }
                    //是可以的，
                    //要求
                    if (flag1 && flag2) {
                        if (((index1 < index2)&&(i<target - i))||((index1 > index2)&&(i>target - i))) {
                            System.out.println("index1: "+index1+" index2: "+index2);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void TwoSum2(int[] datas, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < datas.length; i++) {
            hashMap.put(datas[i], i + 1);
        }

        for (int i = 0; i < datas.length; i++) {
            Integer integer = hashMap.get(target - datas[i]); //
            if (integer != null) {
                if (datas[i] < target - datas[i] && i+1 < integer) {
                    System.out.println("index1: "+(i+1)+" index2: "+integer);
                }
                /*if (datas[i] > target - datas[i] && i+1 > integer) {
                    System.out.println("index1: "+(i+1)+" index2: "+integer);
                }*/
            }

        }

    }

}

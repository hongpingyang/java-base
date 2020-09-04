package com.hong.py.algorithm;

import org.apache.commons.collections.map.HashedMap;

import java.util.Arrays;
import java.util.Map;

/**
 * author: hongpy
 * create time: 2020-06-24 23:49
 * description:
 * life for code
 */
public class Subsets {

    private static  Map<String, int[]> map = new HashedMap();

    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        subsets.subsets(new int[]{1, 2, 3});

        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
        System.out.println("[]");
    }


    public void subsets(int[] datas) {

        int length=datas.length;
        for (int j = 0; j <length ; j++) {
            int[] newResult=new int[]{datas[j]};
            map.put(Arrays.toString(newResult),newResult);
            append(datas, new int[]{datas[j]});
        }
    }

    public void append(int[] datas, int[] result) {

        for (int i = 0; i < datas.length; i++) {
            if (result[result.length - 1] < datas[i]) {
                int[] newResult = new int[result.length+1];
                int j=0;
                for (; j < result.length; j++) {
                    newResult[j]=result[j];
                }
                newResult[j]=datas[i];
                map.put(Arrays.toString(newResult),newResult);
                append(datas,newResult);
            }
        }
    }

}

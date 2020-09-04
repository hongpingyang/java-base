package com.hong.py.algorithm.array;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collections;

/**
 * author: hongpy
 * create time: 2020-06-18 09:52
 * description:
 * life for code
 */
public class TreeSum {

    public static void main(String[] args) {
        TreeSum sum = new TreeSum();
        sum.treeSum(new int[]{-1,0,1,2,-1,-4 },0);
        //排序后的结果是 {-4,-1,-1,0,1,2}

        sum.treeSumClosest(new int[]{-1,2,1,-4 },-1);
    }

    /**
     * 找到3位数组之和等于目标的排序数组
     * @param datas
     * @param target
     */
    public void treeSum(int[] datas, int target) {
        //先排序
        Arrays.sort(datas);
        int lasti=0;
        int lastj=0;
        int lastk=0;
        for (int i = 0; i < datas.length; i++) {
            for (int j = i+1; j < datas.length; j++) {
                for (int k = j+1; k <datas.length ; k++) {
                    if (datas[i] + datas[j] + datas[k] == target) {
                        if (datas[i] == datas[j] && datas[j] == datas[k]) {
                            continue;
                        } else {
                            //只要与上一次结果不同
                            if (datas[i] != lasti || datas[j] != lastj || datas[k] != lastk) {
                                lasti = datas[i];
                                lastj = datas[j];
                                lastk = datas[k];
                                System.out.println(Arrays.asList(datas[i], datas[j], datas[k]));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 找到3位数组之和 与目标最接近的 排序数组
     * @param datas
     * @param target
     */
    public void treeSumClosest(int[] datas, int target) {
        //先排序
        Arrays.sort(datas);
        int lasti=0;
        int lastj=0;
        int lastk=0;
        int mingap=Integer.MAX_VALUE;
        int sum=0;
        for (int i = 0; i < datas.length; i++) {
            for (int j = i+1; j < datas.length; j++) {
                for (int k = j+1; k <datas.length ; k++) {
                    sum=datas[i] + datas[j] + datas[k];
                    if (Math.abs(sum - target) < mingap) {
                        mingap=Math.abs(sum - target);  //找到间隙
                        lasti = datas[i];
                        lastj = datas[j];
                        lastk = datas[k];
                    }
                }
            }
        }

        System.out.println("最接近的数组为：" + Arrays.asList(lasti, lastj, lastk)+" 距离 "+target+" 为:"+mingap);
    }


}

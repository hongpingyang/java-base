package com.hong.py.algorithm.array;

import java.util.HashMap;
import java.util.HashSet;

/**
 * author: hongpy
 * create time: 2020-06-18 08:30
 * description:
 * life for code
 */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();
        longestConsecutiveSequence.longestConsecutiveSequence(new int[]{100,4,200,1,3,2,5,7,8,9,10});
    }


    /**
     * 获取最大的数组长度
     */
    public void longestConsecutiveSequence(int[] datas) {

        HashSet<Integer> set = new HashSet<>();
        for (int data: datas){
            set.add(data);
        }

        int length=datas.length;
        int maxcount=0;
        int maxcount1=0;
        int maxcount2=0;
        int maxvalue=0;
        int afterOrBefore=0;
        for (int i = 0; i < length; i++) {
            int value=datas[i];
            maxcount1=1;
            maxcount2=1;
            //向后排 找到连续的最大值
            for (int j = value+1; j <value+1+length ; j++) {
                if (set.contains(j)) {
                    maxcount1++;
                } else {
                    break;
                }
            }
            //向前排
            for (int j = value-1; j >value-1-length ; j--) {
                if (set.contains(j)) {
                    maxcount2++;
                } else {
                    break;
                }
            }

            int count = Math.max(maxcount1, maxcount2);
            if (count > maxcount) {
                maxcount=count;
                maxvalue=value;
                if(maxcount1>maxcount2)
                    afterOrBefore=1;
                else afterOrBefore=0;
            }
        }

        System.out.println("最大长度:"+maxcount);

        if (afterOrBefore == 1) {
            for (int i = maxvalue; i < maxcount+maxvalue; i++) {
                System.out.print(i+"->");
            }
        }

        if (afterOrBefore == 0) {
            for (int i = maxvalue; i > maxvalue-maxcount; i--) {
                System.out.print(i+"->");
            }
        }
    }
}

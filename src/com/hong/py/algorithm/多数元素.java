package com.hong.py.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * author: hongpy
 * create time: 2020-07-16 22:06
 * description:
 * life for code
 */
public class 多数元素 {

    public static void main(String[] args) {
        countNums(new int[]{1,2,3,4,3,3,3});
        int i = majorityElement(new int[]{1, 2, 3, 4, 3, 3, 3});
        System.out.println(i);
    }

    private static Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            }
            else {
                counts.put(num, counts.get(num)+1);
            }
        }
        return counts;
    }

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = countNums(nums);
        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            //counts.remove(2);
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }
        return majorityEntry.getKey();
    }
}

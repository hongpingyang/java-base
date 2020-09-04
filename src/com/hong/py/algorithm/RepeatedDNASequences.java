package com.hong.py.algorithm;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * author: hongpy
 * create time: 2020-06-17 08:48
 * description:
 * life for code
 */
public class RepeatedDNASequences {

    private static Map<String, Integer> map = new HashedMap();

    /**
     * 找到重复的数字
     * @param dna
     */
    public void repeatedDNASequences(String dna) {
        char[] chars = dna.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i + 10 < chars.length) {
                String substring = dna.substring(i, i + 10);
                Integer orDefault = map.getOrDefault(substring, 0);
                map.put(substring, orDefault + 1);
               /* Integer integer = map.get(substring);
                if (integer == null) {
                    map.put(substring, 1);
                } else {
                    map.put(substring, integer + 1);
                }*/
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {

        RepeatedDNASequences dnaSequences = new RepeatedDNASequences();
        dnaSequences.repeatedDNASequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        for (Map.Entry entry : map.entrySet()) {
            Integer count = (Integer) entry.getValue();
            if (count >= 2) {
                System.out.println(entry.getKey());
            }
        }
    }
}

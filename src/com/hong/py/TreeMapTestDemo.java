package com.hong.py;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/10/14 17:08
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/10/14 17:08
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class TreeMapTestDemo {

    public static void main(String[] args) {

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(5, "val5");
        treeMap.put(6, "val6");
        treeMap.put(11, "val6");
        treeMap.put(9, "val6");
        treeMap.put(16, "val6");
        treeMap.put(1, "val1");
        treeMap.put(3, "val3");
        treeMap.put(2, "val3");
        System.out.println(treeMap);

        Map<String, String> map = new HashMap<>();
        map.put("5", "val5");
        map.put("6", "val6");
        map.put("1", "val1");
        map.put("3", "val3");
        map.put("2", "val3");

        TreeMap<String, String> treeMap1 = new TreeMap<>(map);
        System.out.println(treeMap1);
        
        Map.Entry<Integer, String> integerStringEntry = treeMap.ceilingEntry(4);
        System.out.println("ceilingEntry:"+"返回指定的Key大于或等于的最小值的Key，如果没有，则返回null-------------");
        System.out.println(integerStringEntry.getKey() + ":" + integerStringEntry.getValue());

        Map<Integer,String> integerStringSortedMap = treeMap.subMap(2, 8);
        System.out.println("subMap:"+"截取集合中Key从fromKey到toKey的元素，包括fromKey，不包括toKey-------------");
        for (Map.Entry entry: integerStringSortedMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        SortedMap<Integer, String> integerStringSortedMap1 = treeMap.tailMap(8);
        System.out.println("tailMap:"+"截取Key大于等于fromKey的所有元素-------------");
        for (Map.Entry entry: integerStringSortedMap1.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println("lowerKey:"+"返回小于key最大的Key-------------");
        System.out.println(treeMap.lowerKey(8));

        System.out.println("pollFirstEntry:"+"删除最小的Key-------------");
        treeMap.pollFirstEntry();
        System.out.println(treeMap);

        System.out.println("pollLastEntry:"+"删除最大的Key-------------");
        treeMap.pollLastEntry();
        System.out.println(treeMap);

    }
}

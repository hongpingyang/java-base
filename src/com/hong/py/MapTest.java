package com.hong.py;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MapTest {
    public static void main(String[] args) {

        /**
         * HashMap计算插入节点槽位的方法为：(n - 1) & hash，
         * 由于HashMap的容量总是以2的倍数递增，
         * 所以，扩容后的容量相比于原容量在二进制表达上，
         * 只是最高位前面增加了一位，并且为1。
         * 举个例子，容量为16，n - 1为15（0000 1111），
         * 扩容后的容量为32，n - 1为31（0001 1111），
         * 0001 1111 相比于 0000 1111 只是多了最高位的 1。
         * 因此在于hash值做位与运算时，如果hash值该位为1，
         * 则新槽位 = 原槽位 + 原容量，否则槽位不变。
         */

        int i1 = 31 & (49); //1+16=17

        int i2 = 15 & (49); //1

        int i3 = 16 & (49); //16

        int i4 = 15 & (36); //4

        int i5 = 31 & (36); //4

        int i6 = 16 & (36); //0

        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);

        System.out.println(i4);
        System.out.println(i5);
        System.out.println(i6);

        int n = 20 - 1;
        n |= n >>> 1;
        System.out.println(n);
        n |= n >>> 2;
        System.out.println(n);
        n |= n >>> 4;
        System.out.println(n);
        n |= n >>> 8;
        System.out.println(n);
        n |= n >>> 16;
        System.out.println(n);

        //集合初始化时指定集合初始值大小。随着元素增加，导致扩容，需要重新建hash表，严重影响性能。
        final Map<String,Integer> map=new HashMap();
        //map = new HashMap<>();
        map.put("东方不败",1);
        map.put("岳不群",3);
        map.put("田伯光",5);
        map.put("左冷禅",100);
        //这个不能保存
        map.put(new String("东方不败"),1000);
        //注意HashMap可以插入null值，但ConcurrentHashMap不可以
        map.put(null,null);

         //注意HashMap是无序的，遍历的时候的顺序不一定是加入时候的顺序

        //遍历推荐使用方式2
        //方式1 相当于遍历了2遍
        for (Object key: map.keySet()) {
            System.out.println(key+":"+map.get(key));
        }
        //方式2
        for (Map.Entry entry:map.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

        System.out.println(map.containsKey("令狐冲"));
        System.out.println(map.containsValue(100));

        System.out.println(map);

        /**
         * java8 为map新增的方法
         */
        System.out.println(map.getOrDefault("令狐冲",120));

        map.forEach((o, o2) -> {System.out.println(o);
            System.out.println(o2);});

        ConcurrentMap map1 = new ConcurrentHashMap();
        map1.put("Key", "value");
        map1.putIfAbsent("Key1", "dad");

        //锁的范围太大，没ConcurrentHashMap好
        Map<String, Integer> stringIntegerMap = Collections.synchronizedMap(map);


        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>(map);

        for (HashMap.Entry entry:stringIntegerHashMap.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

        System.out.println("=====================");
        IdentityHashMap<String,Integer> identityHashMap = new IdentityHashMap<String,Integer>();

        //IdentityHashMap的==操作是比较的内存地址，
        // 如果不是指向同一块内存，
        // 那这时候才可以保存相同的数据。
        identityHashMap.put("东方不败",1);
        identityHashMap.put("岳不群",3);
        identityHashMap.put("田伯光",5);
        identityHashMap.put("左冷禅",100);
        //所以这个不能保存
        identityHashMap.put("东方不败",500);
        //所以这个能保存
        identityHashMap.put(new String("东方不败"),1000);
        identityHashMap.put(null,null);

        for (Map.Entry<String,Integer> entry:identityHashMap.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

    }
}

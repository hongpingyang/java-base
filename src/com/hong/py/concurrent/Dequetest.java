package com.hong.py.concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * author: hongpy
 * create time: 2020-07-14 17:00
 * description:
 * life for code
 */
public class Dequetest extends LinkedHashMap<Integer,Integer> {


    public static void main(String[] args) {
        int count=0;






        //ArrayBlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(2);
        //queue.add()
        //LinkedHashMap<Integer,Integer> hashMap=new LinkedHashMap();
        Dequetest cache = new Dequetest( 2 /* 缓存容量 */ );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4

        Dequetest dequetest=new Dequetest(2);
        //dequetest.sortList()

    }



    private int capacity;

    public Dequetest(int capacity) {
        //accessOrder 按照读取顺序排序的。默认为false是按照插入顺序来的
        super(capacity,0.75f,true);
        this.capacity=capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key,-1);
    }

    public void put(int key, int value) {
        super.put(key,value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return super.size()>capacity;
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
}

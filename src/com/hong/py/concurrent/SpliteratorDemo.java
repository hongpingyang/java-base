package com.hong.py.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author: hongpy
 * create time: 2020-04-18 22:37
 * description:
 * life for code
 */
public class SpliteratorDemo {


    public static void main(String[] args) throws InterruptedException {

        Map<String,Integer> map=new HashMap();
        for (int i = 0; i < 20; i++) {
            map.put("测试" + i, i);
        }

        System.out.println(map.size());
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        //多线程并行执行迭代的迭代器
        ConcurrentHashMap<String, Integer> threadExecute = new ConcurrentHashMap<>();
        Spliterator<Map.Entry<String, Integer>> spliterator = entries.spliterator();
        Thread[] threads = new Thread[5];
        for (int i = 0; i <5 ; i++) {
            threads[i]=new Thread(()->{
                 final String name = Thread.currentThread().getName();
                 if (threadExecute.get(name) == null) {
                     threadExecute.put(name,0);
                 }
                 System.out.println();
                 spliterator.trySplit().forEachRemaining((mapEntry)->{
                     System.out.println(mapEntry.getKey()+":"+mapEntry.getValue()+"===="+name);
                     threadExecute.put(name, threadExecute.get(name) + 1);
                 });
             });

            threads[i].start();
        }
        for (int i = 0; i <5 ; i++) {
            threads[i].join();
        }
        //还有需要处理的个数
        System.out.println(spliterator.estimateSize());

        for (Map.Entry<String, Integer> entry : threadExecute.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

    }

}

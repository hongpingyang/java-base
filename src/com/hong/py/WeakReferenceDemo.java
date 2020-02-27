package com.hong.py;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/2/27 17:06
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2020/2/27 17:06
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 hongpy Technologies Inc. All Rights Reserved
 **/
public class WeakReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        //100M的缓存数据
        byte[] cacheData = new byte[100 * 1024 * 1024];
        //将缓存数据用软引用持有
        WeakReference<byte[]> cacheRef = new WeakReference<>(cacheData);
        WeakHashMap<String, String> weakHashMap = new WeakHashMap<>(10);

        String key0 = new String("kuang");
        String key1 = new String("zhong");
        String key2 = new String("wen");
        weakHashMap.put(key0, "q1");
        weakHashMap.put(key1, "q2");
        weakHashMap.put(key2, "q3");

        System.out.println("第一次GC前" + cacheData);
        System.out.println("第一次GC前" + cacheRef.get());

        System.out.println("第一次GC前" + weakHashMap);
        System.out.println("第一次GC前" + weakHashMap.containsKey(key0));

        //进行一次GC后查看对象的回收情况
        System.gc();
        //等待GC
        Thread.sleep(500);
        System.out.println("第一次GC后" + cacheData);
        System.out.println("第一次GC后" + cacheRef.get());

        //将缓存数据的强引用去除
        cacheData = null;
        key0=null;

        System.gc();
        //等待GC
        Thread.sleep(500);
        System.out.println("第二次GC后" + cacheData);
        System.out.println("第二次GC后" + cacheRef.get());

        System.out.println("第二次GC后" + weakHashMap);
        System.out.println("第二次GC后" + weakHashMap.containsKey(key0));
    }
}

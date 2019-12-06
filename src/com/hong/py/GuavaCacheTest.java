package com.hong.py;

import com.google.common.cache.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/9/10 11:21
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/10 11:21
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class GuavaCacheTest {

    /**
     *
     * 愿意消耗一些内存空间来提升速度。
     *
     * 预料到某些键会被多次查询。
     *
     * 缓存中存放的数据总量不会超出内存容量。
     *
     *
     *  基于容量的回收（size-based eviction）  maximumSize设置最大值
     *
     *  定时回收（Timed Eviction）
     *
     *  CacheBuilder提供两种定时回收的方法：
     *
     *  expireAfterWrite    写入后多少时间，则回收
     *  expireAfterAccess  多少时间内没有读写，则回收
     *
     *  基于引用的回收
     *
     *  显式 清除 cache.invalidate
     *
     * @param args
     */
    public static void main(String[] args) throws ExecutionException {

        // 移除监听器
        RemovalListener<String, String> listener = new RemovalListener<String, String>() {
            @Override
            public void onRemoval(RemovalNotification<String, String> removalNotification) {
                System.out.println("[" + removalNotification.getKey() + ":" + removalNotification.getValue() + "] is removed!");
            }
        };

        Cache<String, String> cache = CacheBuilder.newBuilder()

                .maximumSize(2) //设置最大容量2

                .expireAfterWrite(10, TimeUnit.SECONDS)//缓存过去时间，10分钟

                .removalListener(listener) //移除的监听器

                .recordStats()  //开启统计信息

                .build();

        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3"); //导致key1被删除

        List<String> list = new ArrayList<String>();
                 list.add("key1");
                 list.add("key2");
        cache.invalidateAll(list);//批量清除list中全部key对应的记录


        System.out.println("第一个值：" + cache.getIfPresent("key1"));
        System.out.println("第2个值：" + cache.getIfPresent("key2"));
        System.out.println("第3个值：" + cache.getIfPresent("key3"));


        //Cache的get方法有两个参数，第一个参数是要从Cache中获取记录的key，
        // 第二个记录是一个Callable对象。
        // 当缓存中已经存在key对应的记录时，get方法直接返回key对应的记录。
        // 如果缓存中不包含key对应的记录，Guava会启动一个线程执行Callable对象中的call方法，
        // call方法的返回值会作为key对应的值被存储到缓存中，并且被get方法返回
        cache.get("key1", new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println("key1"); //加载数据线程执行标志
                Thread.sleep(1000); //模拟加载时间
                return "auto load by Callable";
            }
        });

        System.out.println("第一个值：" + cache.getIfPresent("key1"));

        System.out.println(cache.stats()); //获取统计信息

        //LoadingCache是Cache的子接口，
        // 相比较于Cache，当从LoadingCache中读取一个指定key的记录时，
        // 如果该记录不存在，则LoadingCache可以自动执行加载数据到缓存的操作。


        CacheLoader<String,String> cacheLoader=new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                Thread.sleep(1000);
                System.out.println(s + "is loaded from cacheLoader");
                return s + "'s value";
            }
        };

        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()

                .maximumSize(2) //设置最大容量2

                .expireAfterWrite(10, TimeUnit.SECONDS)//缓存过去时间，10分钟

                .removalListener(listener) //移除的监听器

                .recordStats()  //开启统计信息

                .build(cacheLoader);

        loadingCache.get("key1");
        loadingCache.get("key2");
        loadingCache.get("key3");





    }
}

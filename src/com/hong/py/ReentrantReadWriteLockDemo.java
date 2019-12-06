package com.hong.py;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: 读写锁
 * @Author: hongpy21691
 * @CreateDate: 2019/8/23 10:04
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/8/23 10:04
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class ReentrantReadWriteLockDemo {

    private static final Map<String, Object> m = new TreeMap<String, Object>();

    private static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    private static final Lock r = rwl.readLock();

    private static final Lock w = rwl.writeLock();

    static ReentrantReadWriteLockDemo demo = new ReentrantReadWriteLockDemo();


    //但是在写线程访问时，
    public static void main(String[] args) {

        for (int i = 0; i <100 ; i++) {
            demo.put(i + "", i);
        }

        //读数据
        for (int i = 0; i < 5; i++) {
            int num=i+1;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = num*10; j < num*10+10; j++) {
                        demo.get(j + "");
                    }
                }
            }).start();
        }

        //写数据
        for (int i = 0; i < 5; i++) {
            int num=i+1;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = num*10; j < num*10+10; j++) {
                        demo.put(j + "", j);
                    }
                }
            }).start();
        }



    }





    public Object get(String key)
    {

        r.lock();

        try
        {
            System.out.println("读线程:"+Thread.currentThread().getName()+m.get(key));
            return m.get(key);

        }

        finally

        {

            r.unlock();

        }

    }



    public String[] allKeys()

    {

        r.lock();

        try

        {

            return (String[]) m.keySet().toArray();

        }

        finally

        {

            r.unlock();

        }

    }



    public Object put(String key, Object value)

    {

        w.lock();

        try

        {
            System.out.println("写线程:"+Thread.currentThread().getName()+value);
            return m.put(key, value);

        }

        finally

        {

            w.unlock();

        }

    }



    public void clear()

    {

        w.lock();

        try

        {

            m.clear();

        }

        finally

        {

            w.unlock();

        }

    }
}

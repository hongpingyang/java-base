package com.hong.py;

import com.hong.py.concurrent.ReentrantReadWriteLock_Source;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

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

    private static final Map<Integer, Object> m = new TreeMap<Integer, Object>();

    private static final ReentrantReadWriteLock_Source rwl = new ReentrantReadWriteLock_Source();

    private static final ReentrantReadWriteLock_Source.ReadLock r = rwl.readLock();

    private static final ReentrantReadWriteLock_Source.WriteLock w = rwl.writeLock();



    static ReentrantReadWriteLockDemo demo = new ReentrantReadWriteLockDemo();


    //
    public static void main(String[] args) {

        for (int i = 0; i <100 ; i++) {
            //demo.put(i, i);
            m.put(i, i);
        }

        Condition condition = w.newCondition();

        //写数据 写锁是独占锁，
        for (int i = 0; i <1; i++) {
            int num=i+1;
            new Thread(new Runnable() {
                @Override
                public void run() {

                    w.lock();
                    System.out.println(Thread.currentThread().getName()+"写锁进入");

                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = num*10; j < num*10+10; j++) {
                        m.put(j, j);
                    }
                    System.out.println(Thread.currentThread().getName()+"写锁退出");

                    w.unlock();
                }
            }).start();
        }


        //读锁是共享锁。
        for (int i = 0; i < 5; i++) {
            int num=i+1;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    r.lock();

                    System.out.println(Thread.currentThread().getName()+"读锁进入");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 1; j++) {
                        System.out.println(m.get(j));
                    }

                    System.out.println(Thread.currentThread().getName()+"读锁退出");

                    r.unlock();
                }
            }).start();
        }




    }





    public Object get(Integer key)
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



    public Object put(Integer key, Object value)

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

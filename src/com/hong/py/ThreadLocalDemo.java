package com.hong.py;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/8/22 16:56
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/8/22 16:56
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 *
 *
 * 由于ThreadLocal对象的set()方法设置的值只对当前线程可见，那有什么方法可以为ThreadLocal对象设置的值对所有线程都可见。
 *
 * 为此，我们可以通过ThreadLocal子类的实现，并覆写initialValue()方法，就可以为ThreadLocal对象指定一个初始化值。如下所示:
 *
 *
 **/
public class ThreadLocalDemo {

    //此时，在set()方法调用前，当调用get()方法的时候，所有线程都可以看到同一个初始化值。
    private static ThreadLocal myThreadLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return   "This is the initial value";
        }
    };


    private static ThreadLocal<Integer> local = new ThreadLocal<>();

    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {

        //ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
           T1 t = new T1(i);
           t.start();
        }

    }

    private static class T1 extends Thread {

        int num;

        public T1(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            doSet(num);
            System.out.println(num);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程"+Thread.currentThread().getName()+" : "+doGet()+" : "+doMyGet());
            //释放
            local.remove();
        }
    }



    private static void doSet(Integer integer) {
        myThreadLocal.set("哈哈"+integer);
        local.set(integer);
    }

    private static String doMyGet() {
        return (String)myThreadLocal.get();
    }

    private static Integer doGet() {
        return local.get();
    }

}

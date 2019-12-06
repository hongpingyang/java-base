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
 **/
public class ThreadLocalDemo {

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
            System.out.println("当前线程"+Thread.currentThread().getName()+" : "+doGet());

        }
    }



    private static void doSet(Integer integer) {
        local.set(integer);
    }

    private static Integer doGet() {
        return local.get();
    }
}

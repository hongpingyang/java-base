package com.hong.py;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/8/22 19:23
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/8/22 19:23
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class FatherAndSonThreadDemo {

    public static void main(String[] args) {
        test();
    }

    /**
     * 子线程如何获取父线程的值  InheritableThreadLocal
     */
    private static void test() {
        ThreadLocal threadLocal = new InheritableThreadLocal();
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("父线程的值");
                System.out.println("父线程"+Thread.currentThread().getName()+threadLocal.get());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("子线程"+Thread.currentThread().getName()+" : "+threadLocal.get());
                    }
                }).start();
            }
        }).start();
    }

}

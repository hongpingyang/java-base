package com.hong.py;

import java.util.concurrent.Semaphore;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/12/12 10:31
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/12/12 10:31
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 *
 * Semaphore
 * 在 semaphore.acquire() 和 semaphore.release()之间的代码，
 * 同一时刻只允许指定个数的线程进入，
 *semaphore.acquire(2) 表示每次线程进入将会占用2个通路，semaphore.release(2) 运行时表示归还2个通路。
 * 没有通路，则线程就无法进入代码块。
 **/
public class SemaphoreTest {

    private volatile  Semaphore executesLimit;

    public static void main(String[] args) throws InterruptedException {

        SemaphoreTest test = new SemaphoreTest();
        //允许的最大并行执行线程为5
        test.getExecutesLimit(5);

        for (int i = 0; i < 10; i++) {
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        action(test);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }

    public static void action(SemaphoreTest test) throws InterruptedException {

        test.executesLimit.acquire(); //内部实现是compareAndSwapInt 自旋锁，会阻塞

        System.out.println(Thread.currentThread().getName() + ":doSomething start-");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + ":doSomething end-" );

        test.executesLimit.release();
    }

    /**
     * 获取信号量
     * @param maxNum
     * @return
     */
    private Semaphore getExecutesLimit(int maxNum) {
        if (executesLimit == null || maxNum > 0) {
            synchronized (this) {
                if (executesLimit == null || maxNum > 0) {
                      executesLimit = new Semaphore(maxNum);
                    }
                }
            }
        return executesLimit;
    }

}

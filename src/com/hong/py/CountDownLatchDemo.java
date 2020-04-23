package com.hong.py;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

public class CountDownLatchDemo implements  Runnable{

    static final CountDownLatch latch = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        //模拟检查任务
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //计数减一
            //放在finally避免任务执行过程出现异常，导致countDown()不能被执行
            latch.countDown();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        //开启10个线程执行，
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i=0; i<10; i++){
            exec.submit(demo);
        }


        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

                //等待全部执行完成 会被唤醒
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 发射火箭
                System.out.println("Fire!");
            }
        });
        thread.start();

        //等待全部执行完成 会被唤醒
        latch.await();
        // 发射火箭
        System.out.println("Fire2!");



        //等待全部执行完成
        latch.await(); //不用等待了 没有进入队列， 已经是state为0了
        // 发射火箭
        System.out.println("Fire3!");
        // 关闭线程池
        exec.shutdown();
    }
}

package com.hong.py.RateLimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.RateLimiter
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/3/31 10:19
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2020/3/31 10:19
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * 使用CyclicBarrier来实现流量突发。也可以使用CountDownLatch
 *
 * CountDownLatch和CyclicBarrier都是用作多线程同步，CountDownLatch基于AQS，
 * CyclicBarrier基于ReentrantLock。
 * CyclicBarrier支持复用和barrierCommand，但是CountDownLatch不支持。
 *
 * 它们的作用就是会让所有线程都等待完成后才会继续下一步行动。
 *
 **/
public class RateLimiterDemo {

    //等待10个wait栅栏
    static CyclicBarrier cyclicBarrier=new CyclicBarrier(10);

    //代表每秒生成10个令牌
    //还可以通过Semaphore来实现。
    static RateLimiter rateLimiter = RateLimiter.create(10);

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Mythread mythread = new Mythread();
            mythread.start();
        }
    }


    public static class Mythread extends Thread {

        @Override
        public void run() {

            try {
                cyclicBarrier.await();

                System.out.println("Thread:" + Thread.currentThread().getName() + "准备完毕,time:" + System.currentTimeMillis());
                boolean b = rateLimiter.tryAcquire(1, TimeUnit.SECONDS);
                //acquire 是阻塞获取，会等待，
                //tryAcquire是非阻塞获取，如果当时获取不到，就立即返回,可以设置等待时间
                //if (b) {
                    //获取到了
                    System.out.println("获取到了"+currentThread()+"["+b+"]");
                //}
                //else { //没有获取到
                    //System.out.println("没有获取到了"+currentThread());
                //}

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }


        }
    }

}

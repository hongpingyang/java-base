package com.hong.py.concurrent;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * author: hongpy
 * create time: 2020-04-13 21:41
 * description:
 * life for code
 *
 *
 * 为线程池中是缓存使用过的线程，当线程被重复调用的时候并没有再重新初始化init()线程，
 * 而是直接使用已经创建过的线程，所以这里的值并不会被再次操作。
 * 因为实际的项目中线程池的使用频率非常高，每一次从线程池中取出线程不能够直接使用之前缓存的变量，
 * 所以要解决这一个问题，网上大部分是推荐使用alibaba的开源项目transmittable-thread-local。
 *
 */
public class InheritableThreadLocalDemo {

    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(1,5,10, TimeUnit.SECONDS,new LinkedBlockingDeque<>());

    public static void main(String[] args) {

        inheritableThreadLocal.set("这是主线程创建的值");

       /* for (int i = 0; i < 5; i++) {
            T1 t1 = new T1(i);
            t1.start();
        }
       */

        threadPoolExecutor.execute(() -> System.out.println("当前线程"+Thread.currentThread().getName()+" : "+inheritableThreadLocal.get()));

        inheritableThreadLocal.set("这是我创建的值");

        threadPoolExecutor.execute(() -> System.out.println("当前线程"+Thread.currentThread().getName()+" : "+inheritableThreadLocal.get()));
    }

    private static class T1 extends Thread {

        int num;

        public T1(int num) {
            this.num = num;
        }

        @Override
        public void run() {

            T2 t2 = new T2(num);
            t2.start();
            System.out.println(num);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程"+Thread.currentThread().getName()+" : "+inheritableThreadLocal.get());

        }
    }

    private static class T2 extends Thread {

        int num;

        public T2(int num) {
            this.num = num;
        }

        @Override
        public void run() {

            System.out.println(num);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程是子线程"+Thread.currentThread().getName()+" : "+inheritableThreadLocal.get());

        }
    }


}

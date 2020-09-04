package com.hong.py;

import com.hong.py.concurrent.AbstractQueuedSynchronizer_Source;
import com.hong.py.concurrent.Semaphore_Source;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

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

    private volatile Semaphore_Source executesLimit;
    private static List<Thread> testThreads = new ArrayList<>();
    public static void main1(String[] args) throws InterruptedException {

        SemaphoreTest test = new SemaphoreTest();
        //允许的最大并行执行线程为5
        //是共享锁
        //他这个是直接设置state了 acquire的时候是去-1  这是与其他锁的区别
        test.getExecutesLimit(2);

        for (int i = 0; i < 5; i++) {
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
            testThreads.add(thread);
            thread.start();
        }

        Thread.sleep(5000);
        //来打断一下线程
        Collection<Thread> queuedThreads = ((test.executesLimit)).getQueuedThreads();
        int i=0;
        for (Thread thread : queuedThreads) {
            if (i == 0) {
                thread.interrupt();
            }
            i++;
        }

        for (Thread thread : testThreads) {
            thread.join();
        }

        System.out.println("重新检查信号量=========");
        for (int y = 0; y < 5; y++) {
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

        //打断会抛异常 如果自己catch也能继续运行，但是没有获取到锁。
        // 后面的release会导致信号量多加
        boolean interrupted=false;
        try {
            test.executesLimit.acquire(); //内部实现是compareAndSwapInt 自旋锁，会阻塞，
        } catch (InterruptedException e) {
            interrupted=true;
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":doSomething start-"+test.executesLimit.getSync().getState());

        Thread.sleep(interrupted?1000:8000);
        System.out.println("....");

        test.executesLimit.release();
        System.out.println(Thread.currentThread().getName() + ":doSomething end-"+test.executesLimit.getSync().getState());
    }

    /**
     * 获取信号量
     * @param maxNum
     * @return
     */
    private Semaphore_Source getExecutesLimit(int maxNum) {
        if (executesLimit == null || maxNum > 0) {
            synchronized (this) {
                if (executesLimit == null || maxNum > 0) {
                      executesLimit = new Semaphore_Source(maxNum);
                    }
                }
            }
        return executesLimit;
    }

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            int count=scanner.nextInt();
            SemaphoreTest m=new SemaphoreTest();
            m.doRun(count);
        }
    }

    public void doRun(int count){
        //System.out.println(count);
        //ExecutorService extor=Executors.newFixedThreadPool(4);
        //extor.execute(new thread1(count));
        //extor.
        // extor.execute(new thread2(count));
        //extor.execute(new thread3(count));
        //extor.execute(new thread4(count));
        Thread thread1=new Thread(new thread1(count));
        thread1.start();
        Thread thread2=new Thread(new thread2(count));
        thread2.start();
        Thread thread3=new Thread(new thread3(count));
        thread3.start();
        Thread thread4=new Thread(new thread4(count));
        thread4.start();

    }

    private static Semaphore s1=new Semaphore(1);
    private static Semaphore s2=new Semaphore(0);
    private static Semaphore s3=new Semaphore(0);
    private static Semaphore s4=new Semaphore(0);

    public  class thread1 implements Runnable {
        private int n;
        public thread1(int n){
            this.n=n;
        }

        @Override
        public void run(){
            for(int i=0;i<n;i++)
            {
                try{
                    s1.acquire(1);
                }catch(Exception e){

                }
                System.out.print("A");
                //s1.release(1);
                s2.release(1); //s2可以执行
            }
        }
    }

    public  class thread2 implements Runnable {
        private int n;
        public thread2(int n){
            this.n=n;
        }
        @Override
        public void run(){
            for(int i=0;i<n;i++)
            {
                try{
                    s2.acquire(1);
                }catch(Exception e){

                }
                System.out.print("B");
                s3.release(1); //s3可以执行
            }
        }
    }

    public  class thread3 implements Runnable {
        private int n;
        public thread3(int n){
            this.n=n;
        }
        @Override
        public void run(){
            for(int i=0;i<n;i++)
            {
                try{
                    s3.acquire(1);
                }catch(Exception e){

                }
                System.out.print("C");
                s4.release(1); //s4可以执行
            }
        }
    }


    public  class thread4 implements Runnable {
        private int n;
        public thread4(int n){
            this.n=n;
        }
        @Override
        public void run(){
            for(int i=0;i<n;i++)
            {
                try{
                    s4.acquire(1);
                }catch(Exception e){

                }
                System.out.print("D");
                s1.release(1); //s1可以执行
            }
        }
    }


}

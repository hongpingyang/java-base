package com.hong.py.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * author: hongpy
 * create time: 2020-04-29 21:20
 * description:
 * life for code
 */
public class ExecutorsDemo {
    public static void main(String[] args) {

        //3种ThreadPoolExecutor


        //new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        //SynchronousQueue是一个没有容量的阻塞队列，每加入一个任务必须要有相应的线程进行移除操作。
        //当主线程提交任务的速度超过线程的执行速度的话，会导致不断创建新的线程。
        ExecutorService executorService = Executors.newCachedThreadPool();

        //LinkedBlockingQueue 无界队列，不会拒绝执行。
        ExecutorService executorService1 = Executors.newFixedThreadPool(5);
        //LinkedBlockingQueue 无界队列，不会拒绝执行。
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        //2种schedule
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        ScheduledExecutorService scheduledExecutorService1 = Executors.newSingleThreadScheduledExecutor();


        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

    }
}

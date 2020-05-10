package com.hong.py.concurrent;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.*;

/**
 * author: hongpy
 * create time: 2020-04-29 21:20
 * description:
 * life for code
 */
public class ExecutorsDemo {
    public static void main(String[] args) {

        //3种ThreadPoolExecutor


       /* //new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        //SynchronousQueue是一个没有容量的阻塞队列，每加入一个任务必须要有相应的线程进行移除操作。
        //当主线程提交任务的速度超过线程的执行速度的话，会导致不断创建新的线程。
        ExecutorService executorService = Executors.newCachedThreadPool();

        //LinkedBlockingQueue 无界队列，正常不会拒绝执行。
        //shutdown的时候会拒绝执行
        ExecutorService executorService1 = Executors.newFixedThreadPool(5);
        //LinkedBlockingQueue 无界队列，
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();*/

        //2种schedule ScheduledThreadPoolExecutor是个无界队列，所以只能设置核心线程数
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        ScheduledExecutorService scheduledExecutorService1 = Executors.newSingleThreadScheduledExecutor();


        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);

        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            Calendar c= Calendar.getInstance();
            System.out.println("定时任务执行"+ c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },1,3, TimeUnit.SECONDS);

    }
}

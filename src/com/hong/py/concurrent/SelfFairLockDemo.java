package com.hong.py.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * author: hongpy
 * create time: 2020-04-13 22:28
 * description:
 * life for code
 *
 * 模拟实现公平锁  利用wait notify 和队列
 */
public class SelfFairLockDemo {


    private static SelfFairLock selfFairLock=new SelfFairLock();
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->

            {
                try {
                    doAction();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            );
            thread.start();
        }

    }

    public static void doAction() throws InterruptedException {
        selfFairLock.Lock();

        Thread.sleep(2000);
        System.out.println("=============");

        selfFairLock.unLock();
    }



    public static class SelfFairLock {

        private List<QueueObject> waitingThreads = new ArrayList<QueueObject>();

        private Thread nowThread;

        public void Lock() throws InterruptedException {

            if (nowThread.equals(Thread.currentThread())) { //同一个线程
                return;
            }

            QueueObject queueObject = new QueueObject();

            //加入队列
            synchronized (this) {
                System.out.println(Thread.currentThread() + "当前线程进入等待");
                waitingThreads.add(queueObject);
            }


            if(waitingThreads.get(0).equals(queueObject))
            {
                queueObject.isNotifyed=true;
                nowThread=Thread.currentThread();
                return;
            }

            try {

                queueObject.doWait();
                System.out.println(Thread.currentThread() + "当前线程开始执行");
                nowThread=Thread.currentThread();

            } catch (InterruptedException e) {
                synchronized (this) {
                    waitingThreads.remove(queueObject);
                }
                e.printStackTrace();
                throw e;
            }
        }

        public void unLock() {

            if (nowThread.equals(Thread.currentThread())) { //同一个线程
                return;
            }

            synchronized (this) {

                System.out.println(Thread.currentThread() + "当前线程退出");
                if (waitingThreads.size() > 0) {
                    for (QueueObject queueObject:waitingThreads) {
                        if (!queueObject.isNotifyed) {
                            queueObject.doNotify();
                            break;
                        }
                    }
                }
            }
        }

        public class QueueObject {

            boolean isNotifyed=false;

            public synchronized void doWait() throws InterruptedException {
                this.wait();
            }

            public synchronized void doNotify() {
                isNotifyed=true;
                this.notify();
            }
        }

    }

}

package com.hong.py.concurrent;

import java.util.LinkedList;
import java.util.List;

/**
 * author: hongpy
 * create time: 2020-04-14 22:20
 * description:
 * life for code
 */


public class SelfBlockQueue {

    private static BlockQueue blockQueue = new BlockQueue(10);


    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() ->
            {
                try {
                    Thread.sleep(1000); //也可以被打断
                    blockQueue.enQueue("生产的商品");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

        for (int i = 0; i < 10; i++) {
            Thread thread1 = new Thread(() ->
            {
                try {
                    Object o = blockQueue.deQueue();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread1.start();
        }
    }


    public static class BlockQueue {

        private List queue = new LinkedList();

        private int limit = 0;

        public BlockQueue(int limit) {
            this.limit = limit;
        }

        public synchronized void enQueue(Object item) throws InterruptedException {
            synchronized (this) {
                while (limit == queue.size()) {
                    wait(); //可以被打断
                }
                queue.add(item);
                System.out.println(Thread.currentThread().getName()+":生产了");
                notify();
            }
        }

        public synchronized Object deQueue() throws InterruptedException {
            synchronized (this){
                while (queue.size() == 0) {
                    wait();
                }
                Object remove = queue.remove(0);
                System.out.println(Thread.currentThread().getName()+":获取的");
                notify();
                return remove;
            }
        }
    }

}

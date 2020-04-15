package com.hong.py.concurrent;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

/**
 * author: hongpy
 * create time: 2020-04-15 23:38
 * description:
 * life for code
 *
 * 单向链表结构的无界阻塞队列
 *
 *  -- 1 预占模式--
 * LinkedTransferQueue 采用一种预占模式。意思就是消费者线程取元素时，如果队列为空，那就生成一个节点（节点元素为null）入队，
 * 然后消费者线程被等待在这个节点上，后面生产者线程入队时发现有一个元素为null的节点，生产者线程就不入队了，
 * 直接就将元素填充到该节点，并唤醒该节点等待的线程，被唤醒的消费者线程取走元素，从调用的方法返回。我们称这种节点操作为“匹配”方式。
 *
 * -- 2 松弛度--
 * 为了节省 CAS 操作的开销，LTQ 引入了“松弛度”的概念：在节点被匹配（被删除）之后，不会立即更新head/tail，
 * 而是当 head/tail 节点和最近一个未匹配的节点之间的距离超过一个“松弛阀值”之后才会更新（这个值为 2）。
 * 这个“松弛阀值”一般为1-3，如果太大会降低缓存命中率，并且会增加遍历链的长度；太小会增加 CAS 的开销。
 *
 *
 * -- 3节点自链接--
 * 已匹配节点的 next 引用会指向自身。
 *
 */
public class LinkedTransferQueueDemo {

    public static void main(String[] args){

        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

        Thread product=new Thread(()->{
            String take = null;
            try {
                take = queue.take(); //会阻塞在这里直到。queue.put("第一次");
                System.out.println(take);
               /* take = queue.take(); //一直阻塞
                System.out.println(take);*/
                take = queue.poll(2, TimeUnit.SECONDS); //一直阻塞到2秒
                System.out.println(take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        product.start();

        Thread consumer=new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.put("第一次");
        });
        consumer.start();


    }
}

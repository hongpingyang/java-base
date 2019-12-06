package com.hong.py;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class QueueTest {

    public static void main(String[] args) {
        TestPriorityQueue();
        TestArrayDeque();

        TestLinkedList();
    }



    /**
     * offer是将元素加入到队列的尾。
     * priorityQueue 支持自然排序和
     */
    private static  void TestPriorityQueue()
    {
        PriorityQueue priorityQueue=new PriorityQueue();
        priorityQueue.offer(4);
        priorityQueue.offer(5);
        priorityQueue.offer(-2);
        priorityQueue.offer(100);
        priorityQueue.offer(6);
        priorityQueue.offer(45);
        priorityQueue.offer(22);

        System.out.println(priorityQueue);

        System.out.println(priorityQueue.poll());

    }

    /**
     *Deque 是一个双端队列的接口，ArrayDeque是其实现。是基于数组实现的，默认长度为16。
     *
     */
    private  static  void TestArrayDeque()
    {
        ArrayDeque stack=new ArrayDeque();
        stack.push("东方不败");
        stack.push("岳不群");
        stack.push("田伯光");
        stack.push("左冷禅");

        System.out.println(stack.peek());
        System.out.println(stack);

        //pop会导致出栈
        System.out.println(stack.pop());
        System.out.println(stack);

        System.out.println("===================");

        ArrayDeque FIFO=new ArrayDeque();
        FIFO.offer("东方不败");
        FIFO.offer("岳不群");
        FIFO.offer("田伯光");
        FIFO.offer("左冷禅");

        System.out.println(FIFO.peek());
        System.out.println(FIFO);

        //pop会导致出栈
        System.out.println(FIFO.pop());
        System.out.println(FIFO);

    }

    private  static  void TestLinkedList()
    {
        System.out.println("==============");
        LinkedList linkedList=new LinkedList();

        linkedList.offer("东方不败");
        linkedList.offer("岳不群");
        linkedList.offer("田伯光");
        linkedList.offer("左冷禅");

        for (int i = 0; i <linkedList.size() ; i++) {
            System.out.println(linkedList.get(i));
        }


        System.out.println(linkedList.peekFirst());
        System.out.println(linkedList);
        System.out.println(linkedList.pop());
        System.out.println(linkedList);

        System.out.println(linkedList.pollLast());
        System.out.println(linkedList);
    }
}

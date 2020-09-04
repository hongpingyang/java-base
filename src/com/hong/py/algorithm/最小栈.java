package com.hong.py.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * author: hongpy
 * create time: 2020-07-16 21:04
 * description:
 * life for code
 */
public class 最小栈 {

    /** initialize your data structure here. */

    private Stack<Integer> sta;
    private PriorityQueue<Integer> queue;

    public 最小栈() {
        sta=new Stack<Integer>();
        queue = new PriorityQueue<>((o1, o2) -> o1.compareTo(o2));
    }

    public void push(int x) {
        sta.push(x);
        queue.offer(x);
    }

    public void pop() {
        Integer pop = sta.pop();
        queue.remove(pop);
    }

    public int top() {
        Integer pop = sta.pop();
        queue.remove(pop);
        return pop;
    }

    public int getMin() {
        return queue.peek(); //只是获取堆顶原生，而不出队列
    }


}

package com.hong.py.algorithm;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * author: hongpy
 * create time: 2020-07-15 16:35
 * description:
 * life for code
 */
public class 喝复制可乐 {

    public static void main(String[] args) {
        kele(5);
    }


    public static void kele(int count){

        LinkedBlockingQueue<String> queue=new LinkedBlockingQueue<String>();
        queue.add("Alice");
        queue.add("Bob");
        queue.add("Cathy");
        queue.add("Dave");
        String last="";
        while(queue.size()>0){
            String poll = queue.poll();
            count--;
            if(count==0){
                last=poll;
                break;
            }
            queue.add(poll);
            queue.add(poll);
        }

        System.out.println(last);
    }
}

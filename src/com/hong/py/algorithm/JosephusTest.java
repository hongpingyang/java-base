package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-04-19 13:52
 * description:
 * life for code
 */
public class JosephusTest {

    static  Josephus josephus = new Josephus();
    public static void main(String[] args) {
        for (int i = 1; i <= 41; i++) {
            josephus.InsertNode(i);
        }

        josephus.kill(josephus);

        System.out.println("活下来的:"+josephus.getHead().getVal());
    }



}

package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-04-19 13:22
 * description:
 * life for code
 */
public class MyNode {

    private int val;
    private MyNode next;
    private MyNode pre;

    public MyNode(int val) {
        this.val=val;
        this.next=null;
        this.pre=null;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public MyNode getNext() {
        return next;
    }

    public void setNext(MyNode next) {
        this.next = next;
    }

    public MyNode getPre() {
        return pre;
    }

    public void setPre(MyNode pre) {
        this.pre = pre;
    }
}

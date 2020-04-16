package com.hong.py.jvm;

public class ConstClassInitDemo {
    public static void main(String[] args) {
        //不会执行ConstClass的静态代码块
        System.out.println(ConstClass.HELLO);
    }
}

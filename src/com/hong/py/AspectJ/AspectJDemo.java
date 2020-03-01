package com.hong.py.AspectJ;

public class AspectJDemo {
    public void SayHello() {
        System.out.println("Hello AspectJ");
    }

    public static void main(String[] args) {
        AspectJDemo demo=new AspectJDemo();
        demo.SayHello();
    }
}

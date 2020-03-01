package com.hong.py.AspectJ;

public aspect TxAspect {
    void around():call(void AspectJDemo.SayHello()){
        System.out.println("开始事务...");
        proceed();
        System.out.println("事务结束...");
    }
}

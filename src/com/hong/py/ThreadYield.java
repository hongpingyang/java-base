package com.hong.py;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/2/28 17:10
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2020/2/28 17:10
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 hongpy Technologies Inc. All Rights Reserved
 **/
public class ThreadYield {

    //yield 是暂停当前线程，执行其他当前线程。
    public static void main(String[] args) {
        Myrunable myrunable=new Myrunable("自定义");
        Thread thread1=new Thread(myrunable,"自定义");

        Myrunable myrunable2=new Myrunable("自定义2");
        Thread thread2=new Thread(myrunable2,"自定义2");

        System.out.println("主线程开始执行");
        thread1.start();
        thread2.start();

        System.out.println("主线程执行完毕");
    }
}

package com.hong.py;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/2/28 16:40
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2020/2/28 16:40
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 hongpy Technologies Inc. All Rights Reserved
 **/
public class ThreadJoin extends Thread implements Runnable {

    public static void main(String[] args) {

        Myrunable myrunable=new Myrunable("自定义");
        Thread thread=new Thread(myrunable,"自定义");

        System.out.println("主线程开始执行");
        thread.start();
        thread.run(); //调用线程执行，根本没有开启线程
        try {
            thread.join(); //让thread先执行，当前线程等待。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程执行完毕");

    }

    @Override
    public void run() {
        System.out.println("");
        super.run();
    }
}



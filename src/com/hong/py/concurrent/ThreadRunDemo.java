package com.hong.py.concurrent;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.concurrent
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/4/13 15:12
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2020/4/13 15:12
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 hongpy Technologies Inc. All Rights Reserved
 **/
public class ThreadRunDemo {

    public static void main(String[] args) {
        Thread myThread = new Thread(new myRunable());
        //注意run和start的区别：
        //run并非是myThread执行的，而是由当前的线程执行了。
        myThread.run();
        //myThread.start();
        //myThread.interrupt();
    }

    public static class myRunable implements Runnable {
        @Override
        public void run() {
            System.out.println("this is my runable");
        }
    }
}

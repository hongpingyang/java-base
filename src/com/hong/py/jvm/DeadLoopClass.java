package com.hong.py.jvm;

public class DeadLoopClass {
    static {
        if (true) {
            System.out.println(Thread.currentThread() + "init DealoopClass");
            while (true) {

            }
        }
    }

    /**
     * 注意这里执行main和在DeadLoopClassTest里执行main的区别
     * 这里会执行一开始就执行静态代码块导致一直在循环。线程没有启动。
     * @param args
     */
    public static void main(String[] args) {
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "start");
                DeadLoopClass deadLoopClass = new DeadLoopClass(); //
                System.out.println(Thread.currentThread() + "end");
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}

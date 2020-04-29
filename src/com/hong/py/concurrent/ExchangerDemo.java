package com.hong.py.concurrent;

import java.util.concurrent.Exchanger;

/**
 * author: hongpy
 * create time: 2020-04-29 18:13
 * description:
 * life for code
 *
 * 用于2个线程交换信息。Exchanger做到一个中间转发器的作用，
 *可简单地将Exchanger对象理解为一个包含两个格子的容器，
 * 通过exchanger方法可以向两个格子中填充信息。
 * 当两个格子中的均被填充时，该对象会自动将两个格子的信息交换，然后返回给线程，从而实现两个线程的信息交换。
 *
 * 多个线程交换可以使用多个exchanger
 * 感觉这个好鸡肋啊有啥用到的地方啊
 *
 *
 */
public class ExchangerDemo {

    public static void main(String[] args) {

        Exchanger<Integer> exchanger = new Exchanger<>();
        Exchanger<Integer> exchanger1 = new Exchanger<>();

        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //String name = Thread.currentThread().getName();
                    System.out.println("线程1开始执行1");
                    Integer exchange = exchanger.exchange(1);
                    System.out.println("线程1结果"+exchange);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //String name = Thread.currentThread().getName();
                    System.out.println("线程2开始执行2");
                    //Thread.sleep(1000);
                    Integer exchange = exchanger.exchange(2);
                    //System.out.println("线程"+name+"结果"+exchange);

                    //System.out.println("线程"+name+"开始执行另一个交换");
                    Integer exchange1 = exchanger1.exchange(exchange);
                    System.out.println("线程2结果"+exchange1);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread3=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //String name = Thread.currentThread().getName();
                    System.out.println("线程3开始执行3");
                    //Thread.sleep(2000);
                    Integer exchange = exchanger1.exchange(3);
                    System.out.println("线程3结果"+exchange);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

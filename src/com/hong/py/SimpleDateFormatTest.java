package com.hong.py;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatTest {

    //SimpleDateFormat不是线程安全的，不能这样用
    static  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

    public static void main(String[] args) {

        Date d=new Date();
        SimpleDateFormat  simpleDateFormat=new SimpleDateFormat("Gyyyy年中第D天");
        String dateStr=simpleDateFormat.format(d);
        System.out.printf(dateStr);
        //输出结果：公元2019年中的第212天


        for (int i = 0; i < 5; i++) {
            final int ti=i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000*ti);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Date d=new Date();
                    String dateStr=dateFormat.format(d);
                    System.out.println(dateStr);
                }
            }).start();
        }
    }
}

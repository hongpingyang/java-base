package com.hong.py;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatTest {

    //SimpleDateFormat不是线程安全的，不能这样用
    static  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

    //可以使用ThreadLocal来保证线程安全
    private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static void main(String[] args) {

        DateFormat dateFormat = df.get();

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
                    String dateStr= SimpleDateFormatTest.dateFormat.format(d);
                    System.out.println(dateStr);
                }
            }).start();
        }
    }
}

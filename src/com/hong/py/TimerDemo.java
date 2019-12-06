package com.hong.py;

import java.sql.Time;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerDemo {

    public static void main(String[] args) {

        Timer timer=new Timer();

//        try {
//            //果然任务1执行异常会导致任务2也会退出执行。推荐使用ScheduledExecutorService
//            timer.schedule(new TimerTaskDemo(1), 5000);
//
//            timer.schedule(new TimerTaskDemo(2), 1000, 2000);
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }

        ScheduledExecutorService executorService= Executors.newSingleThreadScheduledExecutor();

        executorService.schedule(new TimerTaskDemo(3), 0, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(new TimerTaskDemo(4), 1L, 2L, TimeUnit.SECONDS);

        System.out.println("主线程");
    }
}

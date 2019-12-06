package com.hong.py;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/9/25 11:01
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/25 11:01
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class QuartzTest {

    public static void main(String[] args) {

        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .usingJobData("name", "sdas")
                .build();
        //定义一个Trigger
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                //加入 scheduler之后立刻执行
                .startNow()
                //定时 ，每个1秒钟执行一次
                //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1)
                //定时。每天11点每秒执行一次
                .withSchedule(CronScheduleBuilder.cronSchedule("* * 11 * * ?"))
                        //重复执行
                        .build();

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start(); //运行一段时间后关闭
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //scheduler.shutdown();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}

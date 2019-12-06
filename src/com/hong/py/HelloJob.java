package com.hong.py;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/9/25 11:02
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/25 11:02
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        String name = jobDetail.getJobDataMap().getString("name");
        System.out.println("my job name is  " + name + " at " + new Date());
    }

    //也可以自己定义方法，此时需要jobdetail调用时用targetMethod指定
    public void doHandle()
    {

    }
}

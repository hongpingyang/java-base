package com.hong.py;

import java.util.TimerTask;

public class TimerTaskDemo extends TimerTask {

    private  int taskId;
    public TimerTaskDemo(int id) {
        this.taskId=id;
    }

    @Override
    public void run() {
        if (taskId == 1 || taskId == 3) {
            int i = 10 / 0;
        }
        System.out.println("任务"+taskId+"正在执行");
    }
}

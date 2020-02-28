package com.hong.py;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/2/28 17:01
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2020/2/28 17:01
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 hongpy Technologies Inc. All Rights Reserved
 **/
public class Myrunable implements  Runnable {

    private String _name;

    public Myrunable(String RunableName) {
        _name=RunableName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            //try {
                //Thread.sleep(100);
                if(_name.equals("自定义"))
                    Thread.yield();
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
            System.out.println("这是我自定义的线程运行["+_name+"]"+i);
        }
    }
}

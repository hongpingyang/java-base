package com.hong.py.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.concurrent
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/4/20 15:07
 * @Version: 1.0
 * ////////////////////////////////////////////////////////////////////
 * //                          _ooOoo_                               //
 * //                         o8888888o                              //
 * //                         88" . "88                              //
 * //                         (| ^_^ |)                              //
 * //                         O\  =  /O                              //
 * //                      ____/`---'\____                           //
 * //                    .'  \\|     |//  `.                         //
 * //                   /  \\|||  :  |||//  \                        //
 * //                  /  _||||| -:- |||||-  \                       //
 * //                  |   | \\\  -  /// |   |                       //
 * //                  | \_|  ''\---/''  |   |                       //
 * //                  \  .-\__  `-`  ___/-. /                       //
 * //                ___`. .'  /--.--\  `. . ___                     //
 * //              ."" '<  `.___\_<|>_/___.'  >'"".                  //
 * //            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
 * //            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
 * //      ========`-.____`-.___\_____/___.-`____.-'========         //
 * //                           `=---='                              //
 * //      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
 * //            佛祖保佑       永不宕机     永无BUG                  //
 * ////////////////////////////////////////////////////////////////////
 * <p>
 * Copyright © 2020 hongpy Technologies Inc. All Rights Reserved
 **/
public class ForkJoinTaskDemo {

    private static final Integer THRESHOLD =10;

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        NRecursiveTask nRecursiveTask = new NRecursiveTask(list);
        //nRecursiveTask.
        //System.out.println(nRecursiveTask.join());
    }

    public static class  NRecursiveTask extends RecursiveTask<Integer>{

        private List<Integer> list;

        public NRecursiveTask(List<Integer> list) {
            this.list = list;
        }

        @Override
        protected Integer compute() {
            if (list.size() <= THRESHOLD) {
                doCompute();
            }
            //分为2个任务
            NRecursiveTask nRecursiveTask1 = new NRecursiveTask(list.subList(0, list.size() / 2));
            NRecursiveTask nRecursiveTask2 = new NRecursiveTask(list.subList(list.size() / 2,list.size()));

            invokeAll(nRecursiveTask1,nRecursiveTask2);

            return nRecursiveTask2.join()+nRecursiveTask1.join();
        }

        private Integer doCompute() {
            Integer sum=0;
            if (this.list != null) {
                for (int i = 0; i < list.size(); i++) {
                    sum += list.get(i);
                }
            }
            return sum;
        }

    }

}

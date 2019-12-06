package com.hong.py;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/8/22 15:37
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/8/22 15:37
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {
        //多綫程操作集合會導致這個報錯
        //testMultiThread();
        //CopyOnWriteArrayList则不会,读写分离,最终一致性
        testCopyOnWriteArrayListThread();
    }

    private static void testMultiThread() {

        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < 10000; i++)
        {

            list.add(i);
        }



        T1 t1 = new T1(list);

        T2 t2 = new T2(list);

        t1.start();

        t2.start();
    }

    private static void testCopyOnWriteArrayListThread() {

        List<Integer> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 100; i++)
        {
            list.add(i);
        }



        T1 t1 = new T1(list);

        T2 t2 = new T2(list);

        T3 t3 = new T3(list);

        t1.start();

        t2.start();

        t3.start();
    }


    public static class T1 extends Thread
    {

        private List<Integer> list;

        public T1(List<Integer> list)
        {
            this.list = list;
        }

        public void run()
        {

            for (Integer i : list)
            {
                System.out.println("查询"+Thread.currentThread().getName()+" : "+ i);
            }
        }

    }


    public  static class T2 extends Thread
    {
        private List<Integer> list;

        public T2(List<Integer> list)
        {
            this.list = list;
        }
        public void run()
        {
            for (int i = 0; i < list.size(); i++)
            {
                list.remove(i);
                System.out.println("删除"+Thread.currentThread().getName()+" : "+ i);
            }
        }
    }

    public  static class T3 extends Thread
    {
        private List<Integer> list;

        public T3(List<Integer> list)
        {
            this.list = list;
        }
        public void run()
        {
            for (int i = 100; i < 200; i++)
            {
                list.add(i);
                System.out.println("添加"+Thread.currentThread().getName()+" : "+ i);
            }
        }
    }
}

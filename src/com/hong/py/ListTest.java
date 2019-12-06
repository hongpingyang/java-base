package com.hong.py;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {

    public static void main(String[] args) {
        List list=new ArrayList<>();
        list.add("张三丰");
        list.add("李四");
        list.add("王五锤子");
        list.add("二麻子");
        list.remove(1);

        System.out.println(list);

        LinkedList<String> lis = new LinkedList<>();
        lis.add("张三丰");
        lis.add("李四");
        lis.add("王五锤子");
        lis.add("二麻子");
        String s = lis.get(2);
        System.out.println(s);

        System.out.println(lis);

        List<String> listc = new CopyOnWriteArrayList<>();
        listc.add("张三丰");
        listc.add("李四");
        listc.add("王五锤子");
        listc.add("二麻子");


        //支持ListIterator，支持向前迭代。Iterator 只支持向后迭代。

        ListIterator listIterator = list.listIterator();

        while(listIterator.hasNext())
        {
            System.out.println(listIterator.next());
        }
        System.out.println("==========================");
        while(listIterator.hasPrevious())
        {
            System.out.println(listIterator.previous());
        }

        list.replaceAll(o -> o.toString().length());

        System.out.println(list);

        //固定长度的list
        List fixlist= Arrays.asList("乔峰","令狐冲","方世玉");
        //这句话会导致异常。asList 的返回对象是一个 Arrays 内部类，
        // 并没有实现集合的修改方法。
        // Arrays . asList体现的是适配器模式，只是转换接口，后台的数据仍是数组。
        fixlist.add("扫地神僧");


        ArrayList<Integer> list1 = new ArrayList<>();
        Set<Integer> set = new HashSet(list1);
        for (int i = 0; i <= Integer.MAX_VALUE; i++) {
            // 时间复杂度O(1)
            set.contains(i);
        }

    }


    public static class T2 extends Thread
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
            }
        }
    }

}

package com.hong.py;

import java.util.TreeSet;


/**
 *
 *
 *
 */
public class TreeSetTest {

    public static void main(String[] args) {
        TreeSet set=new TreeSet();
        set.add(9);
        set.add(-1);
        set.add(10);
        set.add(5);
        //按照从小到大排序
        System.out.println(set);

        System.out.println(set.first());

        System.out.println(set.last());

        System.out.println(set.headSet(6));

        System.out.println(set.tailSet(5));

        System.out.println(set.subSet(-2,8));
    }


}

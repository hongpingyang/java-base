package com.hong.py.algorithm;

import java.util.Random;

/**
 * author: hongpy
 * create time: 2020-06-17 09:45
 * description:
 * life for code
 * 在2.5亿个整数中找出不重复的数，注意，内存不足以容纳2.5亿个整数。
 * 对于整数相关的算法的求解，位图法是一种非常实用的算法。如果可用的内存空间超过1GB就可以使用这种方法。
 * 具体思路：假设整数占用4B（如果占用8B，那么求解思路类似，只不过需要占用更大的内存），4B也就32位，可以表示的整数的个数为2^32.
 * 由于题目中只查找不重复的数，而不关心具体数字出现的次数，因此可以分别使用2bit来表示各个数字的状态：
 * 用00表示这个数字没有出现过，01表示出现过一次，10表示出现过多次，11暂不使用。
 * 根据上面的逻辑，在遍历这2.5亿个整数的时候，如果这个整数对应的位图的位为00，那么修改为01，如果为01那么改为10，如果为10则保持不变。
 * 这样当所有数据遍历完成后，可以再遍历一遍位图，位图为01的数字就是没有重复的数字。
 */
public class SingleOne {

    private int num;
    private int[] data;
    private int[] dataSingle; //这个表明存在多个

    public SingleOne(int num) {
        this.num = num;
        this.data = new int[num >> 5]; //一个int是32位，2的5次方
        this.dataSingle = new int[num >> 5]; //一个int是32位，2的5次方
    }


    public void add(int value) {
        int index=value>>5;
        int location=value&31;
        data[index]|=(1<<location);//32位bit上置为1表示这个数存在
    }

    public void addSingle(int value) {
        int index=value>>5;
        int location=value&31;
        dataSingle[index]|=(1<<location);//32位bit上置为1表示这个数存在
    }

    public void find(int value) {
        int index=value>>5;
        int location=value&31;
        int i = data[index] & (1 << location);
        if (i == 0) {
            System.out.println("这个数是不存在的");
        } else {
            System.out.println("这是数之前存在的");
        }
    }

    /**
     * 是否只有一个
     * @param value
     * @return
     */
    public boolean isSingle(int value) {
        int index=value>>5;
        int location=value&31;
        int i = data[index] & (1 << location);
        if (i == 0) {
            add(value);
            return true;
        } else {
            addSingle(value);
            return false;
        }
    }


    public static void main(String[] args) {

        Random r = new Random();
        //1亿条测试数据 找出不重复的数据
        int numcount = 100000000;
        SingleOne singleOne=new SingleOne(numcount);
        for (int i = 0; i < numcount; i++) {
            int nextInt = r.nextInt(numcount);
            singleOne.isSingle(nextInt);
        }




    }

}

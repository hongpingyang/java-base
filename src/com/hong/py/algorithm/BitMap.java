package com.hong.py.algorithm;

import java.util.Random;

/**
 * author: hongpy
 * create time: 2020-06-16 20:12
 * description:
 * life for code
 */
public class BitMap {

    private int num;
    private int[] data;

    public BitMap(int num) {
        this.num = num;
        data=new int[num >> 5];
    }

    public void add(int value) {
        int index=value>>5;
        int location=value&31; //value%32
        data[index] |= (1 << location);
    }

    public boolean find(int value) {
        int index=value>>5;
        int location=value&31;
        int i = data[index] & (1 << location);
        if (i == 0) {
            return false;
        }
        return true;
    }

    public void delete(int value) {
        int index=value>>5;
        int location=value&31;
        data[index] &=(~(1 << location));
    }

    public static void main(String[] args) {
        Random r = new Random();
        long currentTimeMillis = System.currentTimeMillis();
        //3亿条数据
        int numcount = 100000000;
        BitMap bitMap=new BitMap(numcount);
        for (int i = 0; i < numcount; i++) {
            int nextInt = r.nextInt(numcount);
            bitMap.add(nextInt);
        }
        int value=99999;

        bitMap.add(value);
        //bitMap.delete(value);

        if (bitMap.find(value)) {
            System.out.println("包含");
        } else {
            System.out.println("不包含");
        }

        //1亿6003ms
        //3亿25064ms
        System.out.println("耗时：" + (System.currentTimeMillis() - currentTimeMillis) + "ms");
    }


}

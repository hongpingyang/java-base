package com.hong.py;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.pojo
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/9/23 16:46
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/23 16:46
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class Hello {

    {
        System.out.println("this is an initBlock");
    }

    public static void main(String[] args) {
        int i=2147483647;
        Integer xad=(Integer) i;
        xad.intValue();

        int ad= (int)xad;

        long sa= i+2L;

        System.out.println("");

        System.out.println(sa);
        float asd=1.3f;
        int x=100;
        long d= x+2;
        System.out.println(d);

        Double d1=Double.NaN;
        Double d2=35867d;
        //注意Double里的compare的写法，考虑了NaN
        System.out.println(d1.compareTo(d2));

        int l = Integer.numberOfLeadingZeros(16) | (1 << (16 - 1));
        System.out.println((l<<16)+2);
        System.out.println((l<<16)+1);
        System.out.println(l);
    }

    private String name;


    public Hello(){

    }


    public void say(String name) {
        System.out.println("Hello"+name);
    }

    public Object newInstance(String name) {
        this.name=name;
        return this;
    }
}


package com.hong.py;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/8/22 20:24
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/8/22 20:24
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class StringDemo {
    public static void main(String[] args) {

        String s = new String("123");
        System.out.println(s.getBytes());
        String s1 = "123";
        System.out.println(s1.getBytes());
        System.out.println(s1==s);

        String s2 = "123";
        System.out.println(s2.getBytes());
        System.out.println(s1==s2);

        //字符相同。所以hashcode()相同
        System.out.println(s1.hashCode()
                +"----"+s.hashCode()
                +"-----"+s2.hashCode());

        // s1和s是不同的字符串对象。不同
        System.out.println(System.identityHashCode(s1)
                +"-----"+System.identityHashCode(s));

        // s1和s2是相同的字符串对象。相同
        System.out.println(System.identityHashCode(s1)
                +"-----"+System.identityHashCode(s2));

    }
}

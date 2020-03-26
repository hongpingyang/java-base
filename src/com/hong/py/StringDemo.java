package com.hong.py;

import java.util.ArrayList;

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
        System.out.println(s1.getBytes().toString());
        System.out.println(s1==s);

        byte[] by1 = s1.getBytes();
        for(int i=0;i<by1.length;i++) {
            System.out.println(by1[i]);
        }

        String s2 = "123";
        System.out.println(s2.getBytes().toString());
        System.out.println(s1==s2);

        byte[] by2 = s2.getBytes();
        for(int i=0;i<by2.length;i++) {
            System.out.println(by2[i]);
        }
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

        String s3 = DealStr1("?11000?01?", -1);
        for (String str:results) {
            System.out.println(str);
        }
    }
    static ArrayList<String> results =new ArrayList<String>();

    public static String DealStr1(String str,int num) {
        if (!str.isEmpty()) {
            if(!str.contains("?"))
            {
                results.add(str);
            }
            for (int i = 0; i < str.length(); i++) {
                if(str.substring(i,i+1).equals("?")) {
                    DealStr1(str.substring(0,i)+"1"+str.substring(i+1),1);
                    DealStr1(str.substring(0,i)+"0"+str.substring(i+1),0);
                    if(num==1) {
                        return str.substring(0, i) + "1" + str.substring(i + 1);
                    }
                    else if(num==0){
                        return str.substring(0, i) + "0" + str.substring(i + 1);
                    }
                    else
                    {
                       return "";
                    }
                }
            }
        }
        return "";
    }

}

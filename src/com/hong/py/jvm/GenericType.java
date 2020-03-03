package com.hong.py.jvm;

import java.util.List;

public class GenericType {

    //入参为泛型不认为是重载
    /*public void method(List<String> list) {
        System.out.println("this is List<String>");
    }

    public void method(List<Integer> list) {
        System.out.println("this is List<Integer>");
    }*/

    public static void main(String[] args) {
        Integer a=1;
        Integer b=2;
        Integer c=3;
        Integer d=3;
        Integer e=321;
        Integer f=321;

        Long g=3L;

        System.out.println(c==d);
        System.out.println(e==f);
        System.out.println(c==(a+b));
        System.out.println(c.equals(a+b));
        System.out.println(g==(a+b));
        System.out.println(g.equals(a+b));
    }
}

package com.hong.py.jvm;

import java.util.List;

public class GenericType {

    //入参为泛型不认为是重载,编译后泛型类型会擦除，导致这2个函数一致。
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

        System.out.println(c==d); //true
        System.out.println(e==f); //false
        System.out.println(c==(a+b)); //true
        System.out.println(c.equals(a+b)); //true
        //
        System.out.println(g==(a+b));  //true
        //equals 先会判断类型是否相同
        System.out.println(g.equals(a+b)); //false



        //泛型的静态变量是同一个，都会关联到同一个字节码上
        Gav t1 = new Gav<String>();
        t1.val="这是个测试";
        Gav t2 = new Gav<Integer>();
        t2.val="这是个测试2";
        System.out.println(t1.val);
        System.out.println(t2.val);



        //三元表达式的坑
        //三元表达式的类型转化规则：
        //1:若两个表达式类型相同，返回值类型为该类型；
        //2:若两个表达式类型不同，但类型不可转换，返回值类型为 Object 类型；
        //3:若两个表达式类型不同，但类型可以转化，先把包装数据类型转化为基本数据类型，
        // 然后按照基本数据类型的转换规则 （byte < short(char)< int < long < float < double） 来转化，
        // 返回值类型为优先级最高的基本数据类型。
        boolean condition=false;
        Integer x1=100;
        Integer x2=100;
        Integer x3=null;

        int i = x3.intValue();
        //x1*x2 类型为int,x3为Integer,所以x3会转换为int，导致报错。
        Integer result = (condition? x1*x2 : x3);
        //会报空指针异常。
        System.out.println(result);
    }

    public static class Gav<T> {

        private static String val;

        public Gav() {
        }
    }
}

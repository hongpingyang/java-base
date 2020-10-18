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

    //在构造方法之前调用
    {
        System.out.println("this is an initBlock");
    }

    private static String address="这个是外部类的";
    private String name;
    public int age;

    public static void test() {
        System.out.println("this is a static method");
    }

    public Hello(){
        this.name = "这是外部名称";
        this.age=18;
        System.out.println("this is an contruct");
    }

     //静态内部类
     static class LittleHello {
         private static String address="这个是内部静态类的";
         private String name;
         public int age;
         static  {
            //静态内部类可以访问外围类的静态字段或方法
            System.out.println(address);
            test();
            System.out.println("this is an littleHello");
        }

         public LittleHello() {
             this.name = "这是内部静态名称";
             this.age=19;
         }
     }

    //普通内部类
    class LittleHello1 {
        //private static String address; //不能包静态字段
        private String name1;
        public int age1;
        public LittleHello1() {
            this.name1 = "这是内部名称";
            this.age1=20;
            //可以访问外部类的非静态字段
            System.out.println(name);
            System.out.println(age);
        }
        //不能包含静态代码块
       /* static  {
            //静态内部类可以访问外围类的静态字段或方法
            System.out.println(address);
            test();
            System.out.println("this is an littleHello");
        }*/
    }

    public void visitInternal() {
        System.out.println(address);
        LittleHello littleHello = new LittleHello();
        //必须实例化才能访问，可以访问内部类的私有和公有字段
        System.out.println(littleHello.name);
        System.out.println(littleHello.age);

        LittleHello1 littleHello1 = new LittleHello1();
        //必须实例化才能访问，可以访问内部类的私有和公有字段
        System.out.println(littleHello1.name1);
        System.out.println(littleHello1.age1);


    }


    public static void main(String[] args) {
        short s1=1;
        //s1=s1+1; 这样会报错
        s1+=1;     //这样不会


        Integer integer1 = new Integer(7);
        int x11=7;
        Integer integer2=7;
        Integer integer3=7;
        System.out.println(integer1 == x11);    //true  Integer会自动拆包为int 两个int相比较
        System.out.println(integer1 == integer2); //false
        System.out.println(integer2 == integer3); //true 常量池 -128到127

        System.out.println(x11 >>> 1);

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

        Hello hello = new Hello();
        hello.say("this is a rwar");

        hello.visitInternal();
    }



    public void say(String name) {
        System.out.println("Hello"+name);
    }

    public Object newInstance(String name) {
        this.name=name;
        return this;
    }
}


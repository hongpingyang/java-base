package com.hong.py.jvm;

/**
 * author: hongpy
 * create time: 2020-04-30 10:32
 * description:
 * life for code
 */
public class InterfaceTest {


    interface  Interface0{
        int A=0;
    }

    interface  Interface1 extends Interface0{
        int A=1;
    }

    static class parent implements Interface0, Interface1 {
        public static  int A=2;
    }

    //可以覆盖
    static class sub extends  parent   implements Interface0,Interface1{
        //public static int A=3;
    }

    //Error:(29, 31) java: 对A的引用不明确
    //com.hong.py.jvm.InterfaceTest.parent 中的变量 A 和 com.hong.py.jvm.InterfaceTest.Interface0 中的变量 A 都匹配
    public static void main(String[] args) {
        //System.out.println(sub.A); //这样会报错
        System.out.println(parent.A); //这样不会报错
    }

}

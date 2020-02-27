package com.hong.py;

import com.hong.py.pojo.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/2/27 14:14
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2020/2/27 14:14
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 hongpy Technologies Inc. All Rights Reserved
 *
 * 我们知道，一个对象的创建过程包含两个过程：初始化和实例化
 * 我们在使用一个对象时，JVM首先会检查相关类型是否已经加载并初始化，
 * 如果没有，则JVM立即进行加载并调用类构造器完成类的初始化。
 * 在类初始化过程中或初始化完毕后，根据具体情况才会去对类进行实例化。
 *
 * 实例化时候，java虚拟机就会为其分配内存来存放自己及其从父类继承过来的实例变量。
 * 在为这些实例变量分配内存的同时，这些实例变量先会被赋予默认值(零值)。
 * 在内存分配完成之后，Java虚拟机才会对新创建的对象赋予我们程序员给定的值。
 *
 * 小结：创建一个对象包含下面两个过程：
 * 1、类构造器完成类初始化（分配内存、赋予默认值）
 * 2、类实例化（赋予给定值）
 **/
public class CreateClassWays {

    public static void main(String[] args) {
        way1();
        way2();
        way3();
        way4();
    }

    /**
     * new
     */
    public static void way1()
    {
        Student student=new Student("hongdadiao",30);
        System.out.println(student);
    }

    /**
     * class newInstance
     */
    public static void way2()
    {
        try {
            Class<?> aClass = Class.forName("com.hong.py.pojo.Student");
            Student student = (Student)aClass.newInstance();
            student.setName("hongdadiao");
            student.setAge(28);
            System.out.println(student);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor newInstance
     */
    public static void  way3()
    {
        try {
            Constructor<Student> constructor = Student.class.getConstructor();
            Student student = constructor.newInstance();
            student.setName("hongdadiao");
            student.setAge(28);
            System.out.println(student);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * clone 类型必须先支持克隆
     */
    public static void way4()
    {
        Student student=new Student("hongdadiao",30);
        try {
            Object clone = (Student)student.clone();
            System.out.println(clone);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }


    public void way5()
    {


    }





}

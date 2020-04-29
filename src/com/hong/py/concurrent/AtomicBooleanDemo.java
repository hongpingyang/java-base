package com.hong.py.concurrent;

import com.hong.py.pojo.Student;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * author: hongpy
 * create time: 2020-04-14 22:57
 * description:
 * life for code
 *
 * 原子操作类
 */
public class AtomicBooleanDemo {

    //本质是转换为int 存入1或者0
    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    private static int[] arrays=new int[]{0,1};


    public static void main(String[] args) {

        //内部复制了一份，不影响原有的
        AtomicIntegerArray atomicIntegerArray=new AtomicIntegerArray(arrays);
        boolean b = atomicIntegerArray.compareAndSet(1, 1, 5);

        int i = atomicIntegerArray.get(1);

        System.out.println(i);
        System.out.println(arrays[1]);



        //解决ABA问题
        Student student = new Student();
        student.setName("洪答答");
        student.setAge(18);

        Student student1 = new Student();
        student1.setName("洪da答");
        student1.setAge(28);

        AtomicStampedReference<Student> atomicStampedReference = new AtomicStampedReference<>(student, 0);
        Student reference = atomicStampedReference.getReference();
        System.out.println(reference);

        atomicStampedReference.compareAndSet(student, student1, 0, 1);

        Student reference1 = atomicStampedReference.getReference();
        System.out.println(reference1);

    }

}

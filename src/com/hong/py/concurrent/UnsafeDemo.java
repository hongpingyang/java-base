package com.hong.py.concurrent;

import com.hong.py.pojo.Student;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * author: hongpy
 * create time: 2020-04-14 23:12
 * description:
 * life for code
 *
 */
public class UnsafeDemo {

    static Unsafe unsafe=null;

    static {
        Field theUnsafe = null;
        try {
            theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe)theUnsafe.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InstantiationException {
        CreateTestA();

    }

    //Unsafe类的使用场景
    //===================================================

    /**
     *  allocateInstance创建但并不初始化类实例 类会被加载初始化（会执行静态代码块），但类的对象不会初始化（不会执行方法块，和构造函数）
     */
    public static void CreateTestA() throws InstantiationException {

        Student student = (Student) unsafe.allocateInstance(Student.class);
        System.out.println(student.getName()); //
    }


    /**
     * 可以直接修改内存
     *  unsafe.putInt(unsafeA, unsafe.objectFieldOffset(unsafeAField), 40);
     */


    /**
     * Java 数组大小的最大值为 Integer.MAX_VALUE。使用直接内存分配，创建的数组大小受限于堆大小。
     * Unsafe 分配的内存，分配在非堆内存，因为不执行任何边界检查，所以任何非法访问都可能会导致 JVM 崩溃。
     * java.nio 就是使用这个技术
     */
    public void allocataMemory(long size) {
        long memory = unsafe.allocateMemory(size);
        //获取到memory,为分配的起始地址
    }
    // i=memory+偏移地址
    public void pubValue(long i,byte value) {
        unsafe.putByte(i,value);
    }


    /**
     *并发应用
     *JUC中大量运用了CAS操作，可以说CAS操作是JUC的基础。
     * Unsafe中提供了int,long和Object的CAS操作
     */

    /**
     * LockSupport类的park方法和unpark方法，这两个方法主要用来挂起和唤醒线程。
     * LockSupport中的park和unpark方法正是通过Unsafe来实现的：
     *
     */

}

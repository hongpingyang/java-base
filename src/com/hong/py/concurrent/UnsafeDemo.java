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

    private static int ABASE = 0;

    private static  int ASHIFT = 0;

    static {
        Field theUnsafe = null;
        try {
            theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe)theUnsafe.get(null);

            Class<?> ak = ForkJoinTask_Source[].class;
            Class<?> ak1 = long[].class;
            //数组的第一个元素的地址
            ABASE = unsafe.arrayBaseOffset(ak);
            //scale为元素的大小
            int scale = unsafe.arrayIndexScale(ak);
            int scale1 = unsafe.arrayIndexScale(ak1);
            System.out.println("ak:"+scale);
            System.out.println("ak1:"+scale1);
            if ((scale & (scale - 1)) != 0) //这个必须是2的次幂
                throw new Error("data type scale not a power of two");

            System.out.println(Integer.numberOfLeadingZeros(1<<16));
            System.out.println(Integer.numberOfLeadingZeros((1<<32)-1));
            System.out.println(Integer.numberOfLeadingZeros((1<<31)));
            System.out.println(Integer.numberOfLeadingZeros((0)));
            ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
            System.out.println(ASHIFT);

            System.out.println(0x0001L << (32 + 15));
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

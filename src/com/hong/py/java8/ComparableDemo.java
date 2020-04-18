package com.hong.py.java8;

import com.hong.py.pojo.JuniorStudent;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * author: hongpy
 * create time: 2020-04-18 16:49
 * description:
 * life for code
 */
public class ComparableDemo {

    public static void main(String[] args) {

        JuniorStudent student = new JuniorStudent();
        student.setName("周星驰");
        student.setAge(30);
        JuniorStudent student1 = new JuniorStudent();
        student1.setName("洪大洋");
        student1.setAge(18);

        if (IsComparabled()) {
            System.out.println(((Comparable) student).compareTo(student1));
        }
    }

    public static boolean IsComparabled() {
        Type[] genericInterfaces = JuniorStudent.class.getGenericInterfaces();
        Type p;
        Type[] as;
        for (int i = 0; i < genericInterfaces.length; i++) {
            if ((p=genericInterfaces[i]) instanceof ParameterizedType && //是否是泛型
                    ((ParameterizedType)p).getRawType()==Comparable.class) {
                if ((as = ((ParameterizedType) p).getActualTypeArguments()) != null && as.length == 1 &&
                        as[0] == JuniorStudent.class) {
                    System.out.println("当前的Comparable方法"+genericInterfaces[i].toString());
                    //是Comparable类型的
                    return true;
                }
            }
        }

        return false;
    }

}

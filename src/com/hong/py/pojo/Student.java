package com.hong.py.pojo;

import java.util.Comparator;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.pojo
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/9/10 9:39
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/10 9:39
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class Student extends Person implements IStudent,Cloneable,Comparable<Student>, Comparator<Student> {

    private String name;
    private Integer age;
    private Person person;
    public Student() {

    }

    private Student(String name) {
        this.name=name;
    }

    public Student(String name, int age) {
        this.name=name;
        this.age=age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private void setAge() {
        this.age = 18;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * 返回匿名类方法
     * @return
     */
    public Runnable classWithAnonymousClass() {
        return new Runnable() {
            public void run() {
            }
        };
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return this.age-o.getAge();
    }

    @Override
    public int compare(Student o1, Student o2) {
        return 0;
    }
}

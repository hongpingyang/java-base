package com.hong.py.pojo;

/**
 * author: hongpy
 * create time: 2020-05-12 22:30
 * description:
 * life for code
 */
public interface IStudent {

    Integer getAge();

    String getName();


    //1: 如果一个类同时实现接口A和B，接口A和B中有相同的default方法，这时，该类必须重写接口中的default方法
    //2: 如果子类继承父类，父类中有b方法，该子类同时实现的接口中也有b方法（被default修饰），那么子类会继承父类的b方法而不是继承接口中的b方法
    default Integer getAgeDefault() {
        return 18;
    }

    default String getNameDefault() {
        return "这是默认的名称";
    }
}

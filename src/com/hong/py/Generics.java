package com.hong.py;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class Generics {

    public static void main(String[] args) {

        //声明生产者
        List<?> names = Lists.newArrayList("yiifaa");
        //  通配符声明的集合，获取的元素都是Object类型
        List<Object> allNames = Lists.newArrayList("yiifee");

        allNames.addAll(names);

        //  只能以Object迭代元素
        for(Object name: names) {
            System.out.println(name);
        }

        //声明生产者
        List<? extends String> extendsnames = Lists.newArrayList("yiifaa");

        //  声明消费者
        List<String> extendsallNames = Lists.newArrayList("yiifee");
        //  消费生产者的元素
        extendsallNames.addAll(extendsallNames);

        //extendsnames.ad
        //  能更精确地确认元素类型，也比第一种更方便迭代
        for(String name: extendsnames) {
            System.out.println(name);
        }

        //可以作为消费者
        List<? super String> superallNames = Lists.newArrayList("yiifaa");
        List<String> supernames = Lists.newArrayList("yiifee");
        //  可以直接添加泛型元素
        superallNames.addAll(supernames);
        //  也可以添加通配符泛型元素
        List<? extends String> names1 = Lists.newArrayList("yiifee");

        //会报错
        //names1.add(superallNames);

        superallNames.addAll(names1);

        //针对采用“? super T”通配符的集合，对其遍历时需要多一次转型，如下：
        //  只能获取到Object类型
        for(Object name: superallNames) {
            //  这里需要一次转型
            System.out.println(name);
        }
    }

}

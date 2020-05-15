package com.hong.py.java8;

import java.io.File;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.java8
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/3/2 18:25
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2020/3/2 18:25
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 hongpy Technologies Inc. All Rights Reserved
 **/
public class ConsumerDemo {

    public static void main(String[] args) {

        //类似于C#的Action
        Consumer self=i-> System.out.println(i);
        self.accept("dadhadhai");

        Consumer c=System.out::println;
        c.accept("hello world");
        c.accept("hello 洪大洋");
        c.accept("hello baby");
        c.andThen(c).andThen(c).accept("hello world");



        BiConsumer<String,Integer> biConsumer= (String str,Integer integer)-> System.out.println(str+":"+integer);
        //使用类型推断
        BiConsumer<String,Integer> biConsumer1= (str,integer)-> System.out.println(str+":"+integer);
        biConsumer.accept("hello 洪大洋", 18);

        //类似于C#的Func
        Function<String,String> function= str->
        {
            System.out.println(str);
            return "还有没有王法";
        };

        System.out.println(function.apply("冯小刚："));







        File[] files = new File(".").listFiles(File::isHidden);

    }

}

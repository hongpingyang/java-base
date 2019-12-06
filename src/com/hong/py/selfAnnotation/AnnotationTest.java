package com.hong.py.selfAnnotation;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.selfAnnotation
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/10/12 19:25
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/10/12 19:25
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class AnnotationTest {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.hong.py.selfAnnotation.AnnotationTestDemo");
        if (aClass.isAnnotationPresent(HpyAnnotation.class)) {
            HpyAnnotation annotation = aClass.getAnnotation(HpyAnnotation.class);
            System.out.println("AnnotationTestDemo配置了註解：HpyAnnotation：");
            System.out.println(annotation.name());
            System.out.println(annotation.age());
            int[] score = annotation.score();
            for (int i:score) {
                System.out.println(i);
            }
        }
    }
}

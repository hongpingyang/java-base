package com.hong.py.selfAnnotation;

import java.lang.annotation.*;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.selfAnnotation
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/10/12 19:18
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/10/12 19:18
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface HpyAnnotation {
    String name();
    int age() default 18;
    int[] score();
}

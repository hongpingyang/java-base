package com.hong.py.localLock;

import java.lang.annotation.*;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.localLock
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/10/12 20:16
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/10/12 20:16
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Resubmit {

    int delaySeconds() default 20;
}

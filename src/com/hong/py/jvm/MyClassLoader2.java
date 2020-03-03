package com.hong.py.jvm;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.jvm
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/3/3 12:46
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2020/3/3 12:46
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 hongpy Technologies Inc. All Rights Reserved
 **/
public class MyClassLoader2 extends ClassLoader {

    public MyClassLoader2() {
        super(MyClassLoader2.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }
}

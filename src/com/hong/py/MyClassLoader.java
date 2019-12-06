package com.hong.py;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/9/30 10:20
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/30 10:20
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * 自定义类加载器
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 *
 **/
public class MyClassLoader extends URLClassLoader {

    public MyClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public MyClassLoader(URL[] urls) {
        super(urls);
    }

    public MyClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String cname = "F:\\" + name.replace('.', '\\') + ".class";
            byte[] classBytes = Files.readAllBytes(Paths.get(cname));
            Class<?> cl = defineClass(name, classBytes, 0, classBytes.length);
            if (cl == null) {
                throw new ClassNotFoundException(name);
            }
            return cl;
        } catch (IOException e) {
            System.out.print(e);
            throw new ClassNotFoundException(name);
        }
    }
}

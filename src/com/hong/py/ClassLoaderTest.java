package com.hong.py;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/9/30 10:13
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/30 10:13
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class ClassLoaderTest {

    /**
     * 双亲委派模型
     * @param args
     */
    public static void main(String[] args) {

        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println("根类加载器路径：");
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url: urls) {
            System.out.println(url.toExternalForm());
        }
        System.out.println("扩展类加载器路径：");
        URLClassLoader extClassLoader = (URLClassLoader)ClassLoader.getSystemClassLoader().getParent();
        for (URL url: extClassLoader.getURLs()) {
            System.out.println(url.toExternalForm());
        }

        System.out.println("系统类加载器路径：");
        URLClassLoader appClassLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
        for (URL url: appClassLoader.getURLs()) {
            System.out.println(url.toExternalForm());
        }
        System.out.println("-------------------------------");
        try {

            URL url=new URL("file:/F:/com/hong/py/pojo");

            ClassLoader loader = new MyClassLoader(new URL[]{url},ClassLoader.getSystemClassLoader());

            URLClassLoader myClassLoader = (URLClassLoader) loader;
            for (URL url1: myClassLoader.getURLs()) {
                System.out.println(url1.toExternalForm());
            }


            //调用loadClass加载com.hong.py.pojo.Hello类
            //无法加载到该类，因此会调用自定义实现的findClass方法
            Class<?> c = loader.loadClass("com.hong.py.pojo.Hello");
            // Object o = c.newInstance();
            Method m = c.getMethod("main", String[].class);
             //ClassLoader myclassLoader = o.getClass().getClassLoader();
            //System.out.println(myclassLoader);

            //loadClass到invoke这一步才加载静态代码段
            m.invoke(null, (Object) new String[]{});


            //Class<?> aClass = Class.forName("com.hong.py.pojo.Hello");


        } catch (Throwable e) {
            System.out.println(e);
        }

    }


}

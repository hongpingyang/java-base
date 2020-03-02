package com.hong.py.jvm;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        ClassLoader myClassLoader=new ClassLoader() {
            /**
             * 直接重写loadClass不是双亲委派模式。因为在loadClass里是findClass(),不提倡重写loadClass方法
             * @param name
             * @return
             * @throws ClassNotFoundException
             */
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";

                InputStream resourceAsStream = this.getClass().getResourceAsStream(fileName);

                if (resourceAsStream == null) {
                    return super.loadClass(name);
                }
                byte[] b = new byte[resourceAsStream.available()];
                resourceAsStream.read(b);
                return defineClass(name, b, 0, b.length);

                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };


        Object aClass = myClassLoader.loadClass("com.hong.py.jvm.ClassLoaderTest").newInstance();
        System.out.println(aClass.getClass());
        System.out.println(aClass.getClass().getClassLoader());//自定义加载器
        System.out.println(aClass instanceof com.hong.py.jvm.ClassLoaderTest);
        System.out.println(com.hong.py.jvm.ClassLoaderTest.class.getClassLoader()); //应用加载器

    }

}


package com.hong.py.ClassResources;


import com.hong.py.pojo.Student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ResourceDemo {

    private final String path;

    private ClassLoader classLoader;

    private Class<?> clazz;

    public ResourceDemo(String path) {
        this.path = path;
    }

    public ResourceDemo(String path,  ClassLoader classLoader) {
        this.path = path;
        this.classLoader = classLoader;
    }

    public ResourceDemo(String path,  Class<?> clazz) {
        this.path = path;
        this.clazz = clazz;
    }

    public InputStream getInputStream() throws IOException {
        InputStream is;
        if (this.clazz != null) {
            is = this.clazz.getResourceAsStream(this.path);
            System.out.println(this.getClass().getResource("com/hong/py/ClassResources"));
        }
        else if (this.classLoader != null) {
            is = this.classLoader.getResourceAsStream(this.path);
        }
        else {
            is = ClassLoader.getSystemResourceAsStream(this.path);
        }
        if (is == null) {
            throw new FileNotFoundException(" cannot be opened because it does not exist");
        }
        return is;
    }

    public static void main(String[] args) throws IOException {
        ResourceDemo demo=new ResourceDemo("", Student.class);
        InputStream inputStream = demo.getInputStream();
    }
}

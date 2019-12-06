package com.hong.py;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class propertityDemo {

    public static void main(String[] args) throws IOException {

        Properties properties=new Properties();
        //读取配置文件内容
        properties.load(new InputStreamReader(
                Object.class.getResourceAsStream("/readProperty.properties")));

        properties.setProperty("url", "jdbc:mysql//localhost:3306");
        properties.setProperty("name","root");

        System.out.println(properties);


    }
}

package com.hong.py;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hong.py.pojo.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/9/10 9:36
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/10 9:36
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class FastJsonTest {

    /**
     *
     * 有几个重要的类型 抽象类JSON 、子类JSONObject 、子类JSONArray
     * 有几个重要的方法 toJSONString
     *                parseObject
     *                toJSON
     *                toJavaObject
     *                parseArray
     * @param args
     */
    public static void main(String[] args) {

        List<Student> list=new ArrayList<>();
        Student student=new Student("bob",24);
        Student student12=new Student("lily", 23);
        list.add(student);
        list.add(student12);
        System.out.println("*******javaBean  to jsonString*******");
        String str1= JSON.toJSONString(student);
        System.out.println(str1);
        System.out.println(JSON.toJSONString(list));


        System.out.println("******jsonString to javaBean*******");
        Student stu1=JSON.parseObject(str1,Student.class);
        System.out.println(stu1);

        System.out.println("******javaBean to jsonObject******");
        JSONObject jsonObject1=(JSONObject)JSON.toJSON(student);
        System.out.println(jsonObject1.getString("name"));
        System.out.println();

        System.out.println("******jsonObject to javaBean******");
        Student student2=JSON.toJavaObject(jsonObject1, Student.class);
        System.out.println(student2);
        System.out.println();

        System.out.println("*******javaBean to jsonArray******");
        List<Student> stulist=new ArrayList<>();
        for(int i=0;i<5;i++){
            stulist.add(new Student("student"+i, i));

        }
        JSONArray jsonArrays=(JSONArray)JSON.toJSON(stulist);
        for(int i=0;i<jsonArrays.size();i++){
            System.out.println(jsonArrays.get(i));
            System.out.println(jsonArrays.getJSONObject(i));
        }

        System.out.println("*****jsonArry to javalist******");
        List<Student> myList=new ArrayList<>();
        for(int i=0;i<jsonArrays.size();i++){

            Student student3=JSON.toJavaObject(jsonArrays.getJSONObject(i), Student.class);
            myList.add(student3);
        }
        for(Student stu:myList) {
            System.out.println(stu);
        }

        System.out.println("*****jsonString to jsonArray*****");
        JSONArray jArray=JSON.parseArray(JSON.toJSONString(stulist));
        for(int i=0;i<jArray.size();i++){
            System.out.println(jArray.get(i));
            System.out.println(jArray.getJSONObject(i));
        }

    }
}

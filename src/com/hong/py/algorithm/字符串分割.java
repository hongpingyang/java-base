package com.hong.py.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * author: hongpy
 * create time: 2020-07-05 17:41
 * description:
 * life for code
 */
public class 字符串分割 {

    private static List<String> inputs;
    private static List<String> result;


    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        int count=s.nextInt();
        inputs=new ArrayList();
        result=new ArrayList();
        for(int i=0;i<count;i++){
            String value=s.next();
            //if(value.length()%8!=0){
            int length=value.length();
            for(int y=0;y<8 - (length%8);y++){
                value+="0";
            }
            //}
            AddString(value);
        }
        doAction(inputs,count);
    }


    public static void doAction(List<String> inputs, int count){
        for(String str:inputs){
            for(int i=0;i<str.length();i+=8){
                result.add(str.substring(i,i+8));
            }
        }
        for(String str:result){
            System.out.println(str);
        }
    }

    public static int AddString(String str){
        if(str.isEmpty()) return -1;
        inputs.add(str);
        return 0;
    }

    public static int GetLength(){
        return result.size();
    }
}

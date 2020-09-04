package com.hong.py.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * author: hongpy
 * create time: 2020-07-05 16:28
 * description:
 * life for code
 */
public class 逆序字符串 {

    public static void main(String[] args) {
        Main();
    }

    public static void Main(){
        Scanner s=new Scanner(System.in);
        String str=s.nextLine();
        String bigresver = bigresver(str);
        System.out.println(bigresver);

        List<Integer> stirngList = new ArrayList();
        for (Integer i : stirngList) {

        }

    }

    public static String bigresver(String str){
        String[] strs=str.split(" ");
        String result="";
        for(int i=strs.length-1;i>=0;i--){
            result+=resver(strs[i])+" ";
        }
        return result;
    }


    public static String resver(String str){
        int length=str.length();
        char[] result = str.toCharArray();
        for(int i=0;i<str.length()/2;i++){
            char v1=result[i];
            char v2=result[length-i-1];
            result[i]=v2;
            result[length-i-1]=v1;
        }
        return String.valueOf(result);
    }
}

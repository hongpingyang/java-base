package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-08-04 09:40
 * description:
 * life for code
 */
public class 字符串相加 {

    public static void main(String[] args) {
        字符串相加 z=new 字符串相加();
        System.out.println(z.addStrings("1435234656565", "111100243"));
    }


    public String addStrings(String num1, String num2) {
       return dfs(num1,num2,num1.length(),num2.length(),1,"");
    }

    public String dfs(String num1,String num2,int length1,int length2,int index,String result){


        int value1=0;
        int value2=0;
        if(length1>=index) {
            value1 = Integer.parseInt(num1.substring(length1 - index, length1 - index+1));
        }
        if(length2>=index) {
            value2 = Integer.parseInt(num2.substring(length2 - index, length2 - index+1));
        }

        if(length1<index&&length2<index) //退出条件
        {
            System.out.println(result);
            return result;
        }

        int resultLength=result.length();
        int value3=0;
        if(resultLength==index){
            value3=Integer.parseInt(result.substring(resultLength-index,resultLength - index+1));
        }

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(value1+value2+value3).append(result.substring(resultLength - index+1));
        result=stringBuilder.toString();

        index++;
        return dfs(num1,num2,length1,length2,index,result);
    }
}

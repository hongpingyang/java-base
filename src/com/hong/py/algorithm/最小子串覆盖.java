package com.hong.py.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * author: hongpy
 * create time: 2020-07-10 14:51
 * description:
 * life for code
 */
public class 最小子串覆盖 {

    public static void main(String[] args) {
        String s = doAction("ADOBECODEBANC", "ABC");
        System.out.println(s);
    }


    
    
    public static String doAction(String source,String target){

        int[] tarcount=new int[128]; //ascill的长度

        char[] chars=target.toCharArray();
        Set<Character> num=new HashSet<Character>();
        for (int i = 0; i < target.length(); i++) {
            tarcount[chars[i]]++;
            num.add(chars[i]);
        }
        char[] schars=source.toCharArray();

        int count=0;
        int minstep=Integer.MAX_VALUE;
        int minbegin=0;
        int minend=-1;

        for(int begin=0,end=0;begin<=end&&end<source.length();end++){

            tarcount[schars[end]]--;

            if(tarcount[schars[end]]==0){ //--之后还是0说明之前不是0。
                count++;
            }

            while (begin<=end&&tarcount[schars[begin]]<0){ //begin字符是多出来的 多减了。
                tarcount[schars[begin]]++; //回一滴血
                begin++;  //往前挪一挪
            }

            if(count==num.size()){ //都满足了
                if(end-begin<minstep){
                    minstep= end-begin;
                    minbegin=begin;
                    minend=end;
                }
            }
        }
        if(minend==-1) return "";
        return  source.substring(minbegin,minend+1);
    }


}

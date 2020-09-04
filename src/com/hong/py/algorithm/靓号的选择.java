package com.hong.py.algorithm;

import java.util.List;

/**
 * author: hongpy
 * create time: 2020-07-15 18:09
 * description:
 * life for code
 */
public class 靓号的选择 {

    private static int localdistance=Integer.MAX_VALUE;

    public static void main(String[] args) {

    }


    public void dfs(String str,int v,int k, int begin, int step,String result, int distance, List<String> path){
        if(step==k){
            if(distance>=localdistance){
                localdistance=distance;
                path.add(result);
            }
        }

        for(int i=begin;i<str.length();i++){
             int temp=Integer.parseInt(str.substring(begin,begin+1));
             distance+=Math.abs(temp-v);
             str=str.substring(0,begin)+v+str.substring(begin);
             result=str;
             dfs(str,v,k,begin+1,step+1,result,distance,path);
             str=str.substring(0,begin)+temp+str.substring(begin);
             distance-=Math.abs(temp-v);
        }
    }
}

package com.hong.py.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * author: hongpy
 * create time: 2020-07-15 16:51
 * description:
 * life for code
 */
public class 球星评比 {

    public static void main(String[] args) {
        ArrayList<String> arrayList=new ArrayList();
        arrayList.add("acbd");
        arrayList.add("bacd");
        arrayList.add("bdca");
        qx(4, 3, arrayList);
    }

    public static void qx(int num, int count, List<String> votes){

        //HashMap<Integer,Integer> result=new HashMap<>();
        int[] result=new int[num];
        for(String str:votes){
           char[] array=str.toCharArray();
           for(int i=0;i<num;i++){
               result[i]+=(int)array[i];
           }
        }
        int minvalue=0;
        int minIndex=0;
        boolean hassame=false;
        for(int i=0;i<num;i++){
            if(result[i]<minvalue){
                minvalue=result[i];
                minIndex=i;
                continue;
            }
        }

        for(int i=0;i<num;i++){
            if(result[i]==minvalue&&minIndex!=i){
                hassame=true;
            }
        }

        if(hassame) System.out.println(-1);
        else System.out.println(minIndex);

    }
}

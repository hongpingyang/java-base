package com.hong.py.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * author: hongpy
 * create time: 2020-07-05 16:53
 * description:
 * life for code
 */
public class 记负均正 {

    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        List<Integer> inputs=new ArrayList();
        while(s.hasNext()){
            String[] inputArray=s.nextLine().split(" ");
            for(int i=0;i<inputArray.length;i++){
                inputs.add(Integer.parseInt(inputArray[i]));
            }
        }

        doAction(inputs);

    }

    public static void doAction(List<Integer> inputs){
        int count=inputs.size();
        int ccount=0;
        double sum=0;
        for(Integer i:inputs){
            if(i<0) ccount++;
            else sum+=i;
        }
        System.out.println(ccount);
        if(count-ccount==0)
            System.out.println(0);
        else{
            double val=sum/(count-ccount);
            System.out.println(String.format("%.1f",val));
        }
    }
}

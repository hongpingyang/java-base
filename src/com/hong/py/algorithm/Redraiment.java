package com.hong.py.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * author: hongpy
 * create time: 2020-07-05 19:03
 * description:
 * life for code
 */
public class Redraiment {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int count=scanner.nextInt();
        int[] pInput=new int[count];
        List<Integer> result=new ArrayList();
        for(int i=0;i<count;i++){
            String input=scanner.next();
            pInput[i]=Integer.parseInt(input);
        }
        Redraiment main=new Redraiment();
        int re=main.GetResult(count,pInput,0,1,result);
        System.out.println(re+1);
    }


    public int GetResult(int num,int[] pInput,int begin,int step,List<Integer> pResult){


        int[] result=new int[num];
        result[0]=0;
        int maxStep=1;
        for(int i=1;i<num;i++){
            for(int y=i;y>=0;y--){
                if(pInput[i]>pInput[y]) {
                    result[i]=Math.max(result[i],result[y]+1);
                }
            }
            maxStep=Math.max(maxStep,result[i]);
        }

        return maxStep;
        // if(begin==num-2){
        //      if(step>maxStep){
        //         maxStep=step;
        //      }
        //      return 1;
        //  }
        // for(int i=begin;i<pInput.length-2;i++){
        //    if(pInput[i]<pInput[i+1]){ //表示这条路可以走{
        //       pResult.add(pInput[i+1]);
        //      GetResult(num,pInput,i+1,step+1,pResult); //选择走
        //
        //      pResult.remove(pResult.size()-1);
        //      GetResult(num,pInput,i+1,step,pResult); //选择不走
        //   }
        //   else{
        //     GetResult(num,pInput,i+1,step,pResult); //选择不走
        //   }
        //}
        //return 0;
    }

}

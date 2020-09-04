package com.hong.py.algorithm;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * author: hongpy
 * create time: 2020-07-05 20:49
 * description:
 * life for code
 */
public class 数组分家 {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);

        while (scanner.hasNext()) {
            int count=scanner.nextInt();
            int[] inputs=new int[count];
            int sum1=0;
            int sum2=0;
            for(int i=0;i<count;i++){
                inputs[i]=scanner.nextInt();
                if(inputs[i]%5==0) sum1+=inputs[i];
                else if(inputs[i]%3==0) sum2+=inputs[i];
            }
            boolean result=dfs(count,0,sum1,sum2,inputs);
            System.out.println(result);

            System.out.println(Arrays.asList(inputs));

        }
    }

    public static boolean dfs(int n,int begin,int sum1,int sum2,int[] inputs){
        if(begin==n&&sum1==sum2)
            return true;
        else if(begin==n)
            return false;
        if(inputs[begin]%5!=0&&inputs[begin]%3!=0){
            return dfs(n,begin+1,sum1+inputs[begin],sum2,inputs)|| dfs(n,begin+1,sum1,sum2+inputs[begin],inputs); //
        }else{
            return dfs(n,begin+1,sum1,sum2,inputs);
        }
    }
}

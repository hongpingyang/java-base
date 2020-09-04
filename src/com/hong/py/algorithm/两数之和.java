package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-08-02 21:38
 * description:
 * life for code
 */
public class 两数之和 {

    public int[] twoSum(int[] nums, int target) {
       int[] result=new int[2];
       for(int i=0;i<nums.length;i++){
           for(int y=0;y<nums.length;y++){
               if(i!=y){
                   if(nums[i]+nums[y]==target){
                       result[0]=i;
                       result[1]=y;
                   }
               }
           }
       }

       return result;
    }

}

package com.hong.py.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.algorithm
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/3/26 18:48
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2020/3/26 18:48
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * 假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：
 *
 * 第 i 位的数字能被 i 整除
 * i 能被第 i 位上的数字整除
 * 现在给定一个整数 N，请问可以构造多少个优美的排列？
 *
 * 示例1:
 *
 * 输入: 2
 * 输出: 2
 * 解释:
 *
 * 第 1 个优美的排列是 [1, 2]:
 *   第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
 *   第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
 *
 * 第 2 个优美的排列是 [2, 1]:
 *   第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
 *   第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
 *
 **/
public class Day1 {

    private static Map<Integer, ArrayList<int[]>> mapArrayList = new HashMap<>();

    private static int value=0;

    public static void main(String[] args) {

        ArrayList<int[]> arrayList = new ArrayList<>();
        arrayList.add(new int[]{2,1});
        arrayList.add(new int[]{1,2});
        mapArrayList.put(2,arrayList);

        int i = NumN(5);
        System.out.println(mapArrayList.get(5).size());
    }


    public static int NumN(int n) {
        if(n==2)
        {
            return 2;
        }
        int value=0;

        //for (int i = 2; i <=n-1 ; i++) {
            ArrayList<int[]> newarrayList = new ArrayList<>();
            for (int j = 0; j < NumN(n-1); j++) {
                   ArrayList<int[]> arrayList = mapArrayList.get(n-1);

                    if(arrayList!=null)
                    {
                        //for (int[] array: arrayList) {
                            int[] arraynew=new int[n];
                            for (int l = 0; l <n-1 ; l++) {
                                arraynew[l]=arrayList.get(j)[l];
                            }
                            arraynew[n-1]=n;

                            newarrayList.add(arraynew);
                            value+=1;
                            for (int k = 1; k <= n-1 ; k++) {
                            //可以调换
                            if(((n%k)==0||(k%n)==0)&&(arrayList.get(j)[k-1]%n==0||n%arrayList.get(j)[k-1]==0))
                            {
                                int[] arraynew2=new int[n];
                                for (int l = 0; l <n-1 ; l++) {
                                    arraynew2[l]=arrayList.get(j)[l];
                                }
                                arraynew2[n-1]=arrayList.get(j)[k-1];
                                arraynew2[k-1]=n;
                                value+=1;

                                newarrayList.add(arraynew2);
                            }
                          //}

                       }

                }
         //   }
        }
        mapArrayList.put(n,newarrayList);
        return value;
    }

}

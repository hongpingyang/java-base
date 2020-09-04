package com.hong.py.algorithm.dtgh;

/**
 * author: hongpy
 * create time: 2020-06-29 09:53
 * description:
 * life for code
 */
public class DecodeWays {

    private static int[] allResult;

    public static void main(String[] args) {
        DecodeWays decodeWays = new DecodeWays();
        String str = "127";
        allResult = new int[str.length() + 1];
        allResult[0]=1;
        allResult[1]=1;
        int i = decodeWays.decodeWays(str);
        System.out.println(i);
    }

    public int decodeWays(String str) {
        int strLength = str.length();
        for (int i = 2; i <= strLength; i++) {
            String sub = str.substring(i - 2, i); //后推2位数
            if (Integer.parseInt(sub) > 26) { //超过26了就只能依赖前面一个的
                allResult[i] = Math.max(allResult[i], allResult[i - 1]);
            } else {
                allResult[i] = Math.max(allResult[i], allResult[i - 1]+allResult[i - 2]);
            }
        }
        return allResult[strLength];
    }
}

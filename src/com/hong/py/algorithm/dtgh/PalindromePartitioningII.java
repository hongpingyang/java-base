package com.hong.py.algorithm.dtgh;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;
import java.util.Scanner;

/**
 * author: hongpy
 * create time: 2020-06-30 11:46
 * description:
 * life for code
 */
public class PalindromePartitioningII {

    public static void main(String[] args) {
        while (true) {
            Scanner cin = new Scanner(System.in);
            String next = cin.next();
            if (next.equals("quit")) {
                break;
            }
            PalindromePartitioningII palindromePartitioningII = new PalindromePartitioningII();
            palindromePartitioningII.palindromePartitioningII(next);
            palindromePartitioningII.palindromePartitioningII1(next);
        }

    }

    /**
     * 动态规划求解
     * @param str
     */
    public void palindromePartitioningII(String str) {
        int length=str.length();
        int[] result = new int[length];
        byte[] bytes = str.getBytes();
        result[0]=0;
        for (int i = 1; i < length; i++) {
            if (bytes[i] == bytes[i - 1]) {
                result[i] = result[i - 1];//这里不用分割
            } else {
                result[i] = result[i - 1]+1;//这里得需要分割
            }
        }
        System.out.println(result[length-1]);
    }


    private static Map<Integer, Integer> map = new HashedMap();

    public void palindromePartitioningII1(String str) {
        map.clear();
        int i = palindromePartitioningII1(str, str.length() - 1);
        System.out.println(i);
    }
    /**
     * 递归
     * @param str
     * @param n
     * @return
     */
    public int palindromePartitioningII1(String str, int n) {
        if (n == 0) {
           return 0;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        assert n >= str.length() : "this is not allowed";
        byte[] bytes = str.getBytes();
        int result=0;
        if (bytes[n - 1] == bytes[n]) {
            result= palindromePartitioningII1(str, n - 1);
        } else {
            result= palindromePartitioningII1(str, n - 1)+1;
        }
        map.put(n, result);
        return result;
    }
}

package com.hong.py.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * author: hongpy
 * create time: 2020-06-26 16:43
 * description:
 * life for code
 */
public class PalindromePartitioning {

    public static void main(String[] args) {
        PalindromePartitioning palindromePartitioning = new PalindromePartitioning();
        palindromePartitioning.palindromePartitioning("aabc");

    }


    public void palindromePartitioning(String str) {
        List<String> path = new ArrayList<>();
        dfs(str,"",0,path);
        for (String string : path) {
            System.out.println(string.substring(1));
        }
    }


    public void dfs(String str, String result, int begin, List<String> path) {
        if (begin == str.length()) {
            path.add(result);
            result = "";
        }
        for (int i = begin; i < str.length(); i++) {
            //遍历 begin后面每个的切的可能
            dfs(str,result + "," + str.substring(begin, i+1),i+1,path);
        }
    }

}

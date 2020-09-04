package com.hong.py.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * author: hongpy
 * create time: 2020-06-27 20:40
 * description:
 * life for code
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        generateParentheses.generateParentheses(4);
    }


    public void generateParentheses(int n) {
        List<String> allResult = new ArrayList<>();
        dfs(n,0,0,"",allResult);
        for (String str : allResult) {
            System.out.println(str);
        }
    }

    public void dfs(int n,int leftStep,int rightStep,String result, List<String> allResult) {
        if (leftStep > n || rightStep > n) { //收敛条件
            return;
        }
        if (leftStep == n && rightStep == n) { //终止条件  满足的添加结果
            allResult.add(result);
            return; //返回去尝试其它的
        }
        if (leftStep == 0) { //起步必须向左
            result += "("; //这里添加了不能移除了
            dfs(n, leftStep + 1, rightStep, result, allResult);
        }
        else {
            if (leftStep >= rightStep) {
                if (leftStep == n) { //必须向右了
                    result += ")";  //这里添加了不能移除了
                    dfs(n, leftStep, rightStep + 1, result, allResult);
                } else {
                    //可以左也可以右
                    //先向左 一路走到底
                    result += "(";
                    dfs(n, leftStep + 1, rightStep, result, allResult);
                    result = result.substring(0, result.length() - 1);
                    //后向右 一路走到底
                    result += ")";
                    dfs(n, leftStep, rightStep + 1, result, allResult);
                    result = result.substring(0, result.length() - 1);
                }
            }
        }
    }
}

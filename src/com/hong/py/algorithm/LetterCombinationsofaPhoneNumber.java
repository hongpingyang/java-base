package com.hong.py.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * author: hongpy
 * create time: 2020-06-26 11:01
 * description:
 * life for code
 *
 * 深度优先
 */
public class LetterCombinationsofaPhoneNumber {

    public static void main(String[] args) {
        LetterCombinationsofaPhoneNumber letterCombinationsofaPhoneNumber = new LetterCombinationsofaPhoneNumber();
        letterCombinationsofaPhoneNumber.letterCombinationsofaPhoneNumber(new int[]{2, 3});
    }

    private static final String[] keyBoard = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public void letterCombinationsofaPhoneNumber(int[] datas) {
        List<String> path = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        findCombination(datas,0,datas.length,path,result);
        for (List entry : result) {
            System.out.println(entry.toString());
        }
    }

    public void findCombination(int[] datas, int step, int k, List<String> path, List<List<String>> result) {

        if (step == k) { //达到K的那种需要记录下来
            result.add(new ArrayList<>(path));
            return;
        }
        String string = keyBoard[datas[step]]; //取出电话号码
        for (int i = 0; i < string.length(); i++) { //遍历取数的可能
            path.add(string.substring(i, i + 1)); //先添加
            findCombination(datas, step + 1, k, path, result);  //深度去找各种可能
            path.remove(path.size() - 1);  //后移除这个
        }
    }
}

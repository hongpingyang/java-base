package com.hong.py.algorithm;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * author: hongpy
 * create time: 2020-06-26 10:05
 * description:
 * life for code
 */
public class Combinations {

    private static Map<String, List<Integer>> map = new HashedMap();

    public static void main(String[] args) {
        Combinations combinations = new Combinations();
        combinations.combinations(4, 2);
    }

    public void combinations(int n, int k) {
        int[] datas = new int[n];
        for (int i = 0; i < n; i++) {
           datas[i]=i+1;
        }
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        findCombination(datas, 0, 0, k, path, result);
        for (List entry : result) {
            System.out.println(entry.toString());
        }
    }

    public void findCombination(int[] datas, int start, int step, int k, List<Integer> path, List<List<Integer>> result) {
        if (step == k) { //达到K的那种
            result.add(new ArrayList<>(path));
        }
        for (int i = start; i < datas.length; i++) { //遍历取数的可能
            path.add(datas[i]); //先加上的
            findCombination(datas, i + 1, step + 1, k, path, result); //深度优先
            path.remove(path.size() - 1);//后移除的
        }
    }
}

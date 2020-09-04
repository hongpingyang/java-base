package com.hong.py.algorithm.dtgh;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * author: hongpy
 * create time: 2020-06-29 10:27
 * description:
 * life for code
 */
public class PrefectSquare {

    private static int[] result;

    public static void main(String[] args) {
        PrefectSquare prefectSquare = new PrefectSquare();
        int n=12;
        result = new int[n + 1];
        int i = prefectSquare.prefectSquare(n);
        System.out.println(i);
    }

    public int prefectSquare(int n) {

        Map<Integer, List<Integer>> map = new HashedMap();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n / 2; j++) { //j就是为找到能开平方的数
                if (i > j * j) {
                    if (result[i] == 0) { // 数组默认值特殊情况处理下
                        result[i] = result[i - j * j] + 1;
                    } else {
                        result[i] = Math.min(result[i],result[i - j * j] + 1);
                    }

                    if (result[i] >= result[i - j * j] + 1) {
                        List<Integer> list = map.get(i);
                        if (list == null) {
                            list = new ArrayList<>();
                        }
                        List<Integer> list1 = map.get(i - j * j);
                        //需要替换的
                        if (list1 != null && list1.size() > 0 && list.size() > list1.size() + 1) {
                            list.clear();
                            list.addAll(new ArrayList<>(list1));
                            list.add(j * j); //加入平方数
                        } else if(list.size()==0) {
                            list.addAll(new ArrayList<>(list1));
                            list.add(j * j); //加入平方数
                        }
                        map.put(i, new ArrayList<>(list));
                    }

                } else if (i == j * j) { //表明自己就是平方数，为1
                    result[i] = 1;
                    map.put(i, Arrays.asList(j*j));
                } else {
                    break;
                }
            }
        }

        List<Integer> list = map.get(n);
        for (Integer i : list) {
            System.out.println(i);
        }
        return result[n];
    }
}

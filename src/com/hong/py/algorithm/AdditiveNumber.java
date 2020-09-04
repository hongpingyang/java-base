package com.hong.py.algorithm;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * author: hongpy
 * create time: 2020-06-26 15:40
 * description:
 * life for code
 */
public class AdditiveNumber {

    public static Map<Integer, List<Integer>> map = new HashedMap(); //结果集
    public static Map<Integer, Boolean> mapPrint = new HashedMap();  //是否打印过

    public static void main(String[] args) {
        AdditiveNumber additiveNumber = new AdditiveNumber();
        additiveNumber.additiveNumber("112358");
        additiveNumber.additiveNumber("199100199");
    }

    public void additiveNumber(String str) {
        int length = str.length();
        //找到起始点的各种可能
        for (int i = 1; i <=length/2 ; i++) {
            if(str.charAt(0)=='0'&&i>1) continue;
            for (int j = i + 1; j < length; j++) {
                if(str.charAt(i)=='0'&&j>i+1) continue;
                  if(dfs(str, 0, i, j))
                      System.out.println("可以满足");
             }
        }
    }

    /**
     *
     * @param str
     * @param begin  第一个数的起点
     * @param mid    第一个数的终点，第二个数的起点：+1
     * @param end    第二个数的终点
     * @return
     */
    private boolean dfs(String str, int begin, int mid, int end) {
        int value1 = Integer.valueOf(str.substring(begin, mid));
        int value2 = Integer.valueOf(str.substring(mid, end));
        int add=value1+value2;
        if (!str.substring(end).startsWith(String.valueOf(add))) return false; //这种的是不行的
        map.put(add, Arrays.asList(value1,value2));
        if(str.substring(end).equals(String.valueOf(add))) { //符合
            //开始打印
            List<Integer> integers = map.get(add);
            for (Integer integer : integers) {
                 print(integer);
            }
            print(add);
            return true;//到达最后一个数字，满足
        }
        return dfs(str, mid, end, String.valueOf(add).length() + end);
    }

    public void print(Integer value) {
        if (!mapPrint.getOrDefault(value, false)) {
            List<Integer> integers = map.get(value);
            if(integers==null) return;
            StringBuffer stringBuffer = new StringBuffer();
            for (Integer integer : integers) {
                print(integer);
                stringBuffer.append(integer+"+");
            }
            mapPrint.put(value, true);
            String substring = stringBuffer.substring(0, stringBuffer.length() - 1);
            System.out.println(substring+"="+value+";");
        }
    }

}

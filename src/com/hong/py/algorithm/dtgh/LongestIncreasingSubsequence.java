package com.hong.py.algorithm.dtgh;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * author: hongpy
 * create time: 2020-06-28 22:28
 * description:
 * life for code
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        longestIncreasingSubsequence.longestIncreasingSubsequence(new int[]{10,9,2,5,3,4,7,101,18});
    }


    public void longestIncreasingSubsequence(int[] datas) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        //方法1 下面的写法是错误的 可以加入的地方只考虑了加入，没有考虑不加入
        //O(n^2)
        /*for (int i = 0; i < datas.length; i++) {
            temp.clear();
            temp.add(datas[i]);
            for (int j = i+1; j < datas.length; j++) {
                if (datas[j] > temp.get(temp.size() - 1)) { //可以加入
                    temp.add(datas[j]);
                }
            }
            if (temp.size()>= maxstep) {
                maxstep = temp.size();
                result.add(new ArrayList<>(temp));
            }
        }*/


        //方法2 深度遍历
        dfs(datas, 0, 0, temp);


        System.out.println("最大的升序长度为：" + maxstep);
        for (List<Integer> list : map.get(maxstep)) {
            System.out.print("[");
            StringBuffer stringBuffer = new StringBuffer();
            for (Integer integer : list) {
                stringBuffer.append(integer + ",");
            }
            System.out.print(stringBuffer.substring(0, stringBuffer.length() - 1));
            System.out.println("]");
        }


        //方法3 动态规划
        dtgh(datas);
    }

    private static int maxstep;
    private static Map<Integer, List<List<Integer>>> map = new HashedMap();

    public void dfs(int[] datas, int begin, int step,List<Integer> path) {
        if (begin == datas.length) {
            if (maxstep <= step) {
                maxstep = step;
                //可能会造成多余的
                List<List<Integer>> lists = map.get(maxstep);
                if(lists==null)
                    lists = new ArrayList<>();
                if(!lists.contains(path)) //去重
                  lists.add(new ArrayList<>(path));
                map.put(maxstep,lists);
            }
            return;
        }
        for (int i = begin; i < datas.length; i++) {
            if (path.size()==0||datas[i] > path.get(path.size() - 1)) { //这个元素可以加入 可以选择加入或者不加入
                //加入的试试
                path.add(datas[i]);
                dfs(datas, i + 1, step + 1, path);
                //不加入的试试
                path.remove(path.size() - 1);
                dfs(datas, i + 1, step, path);
            } else {
                dfs(datas, i + 1, step,path);
            }
        }
    }

    /**
     * 动态规划 状态转移方程
     * @param datas
     */
    public void dtgh(int[] datas) {
        int[] fill = new int[datas.length];
        Arrays.fill(fill,1); //默认值为1
        int maxglobal=0;
        for (int i = 1; i < datas.length; i++) {
           //遍历i之前的所有节点，来找到i的最大值
            for (int j = 0; j < i; j++) {
                if(datas[i]>datas[j])
                  fill[i] = Math.max(fill[i], fill[j] + 1);
            }
            maxglobal = Math.max(maxglobal, fill[i]);
        }
        System.out.println(Arrays.toString(fill));
        System.out.println("最大的升序长度为：" + maxglobal);
    }

}

package com.hong.py.algorithm;

import java.util.*;

/**
 * author: hongpy
 * create time: 2020-06-27 11:55
 * description:
 * life for code
 */
public class CombinationSum {

    //private static final List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        combinationSum.combinationSum(new int[]{3,4,5,2},7);
        System.out.println("================");
        combinationSum.combinationSum(new int[]{3,4,5,2,7},7);
        System.out.println("================");
        combinationSum.combinationSum1(new int[]{10,1,2,7,6,1,5},8);

        System.out.println("================");
        //求1-9之间的3个数相加为9
        combinationSum.combinationSum2(new int[]{1,2,3,4,5,6,7,8,9},9,3);
    }

    public void combinationSum(int[] set,int target) {
        Arrays.sort(set); //其实不用排序  这里排序可以让结果是升序
        List<List<Integer>> allResult = new ArrayList<>();
        dfs(set, target, 0,0, new ArrayList<>(),allResult);
        for (List<Integer> list : allResult) {
            System.out.print("[");
            StringBuffer stringBuffer=new StringBuffer();
            for (Integer integer : list) {
                stringBuffer.append(integer + ",");
            }
            System.out.print(stringBuffer.substring(0,stringBuffer.length()-1));
            System.out.println("]");
        }
    }

    public void combinationSum1(int[] set,int target) {
        Arrays.sort(set); //这里排序可以让结果是升序
        List<List<Integer>> allResult = new ArrayList<>();
        dfs1(set, target, 0,0, new ArrayList<>(),allResult);
        for (List<Integer> list : allResult) {
            System.out.print("[");
            StringBuffer stringBuffer=new StringBuffer();
            for (Integer integer : list) {
                stringBuffer.append(integer + ",");
            }
            System.out.print(stringBuffer.substring(0,stringBuffer.length()-1));
            System.out.println("]");
        }
    }

    public void combinationSum2(int[] set,int target,int times) {
        Arrays.sort(set); //这里排序可以让结果是升序
        List<List<Integer>> allResult = new ArrayList<>();
        dfs2(set, target, times,0,0,0, new ArrayList<>(),allResult);
        for (List<Integer> list : allResult) {
            System.out.print("[");
            StringBuffer stringBuffer=new StringBuffer();
            for (Integer integer : list) {
                stringBuffer.append(integer + ",");
            }
            System.out.print(stringBuffer.substring(0,stringBuffer.length()-1));
            System.out.println("]");
        }
    }

    /**
     * 每个元素可以使用多次的版本
     * @param set
     * @param target
     * @param begin
     * @param sum
     * @param result
     * @param allResult
     */
    public void dfs(int[] set, int target, int begin, int sum, List<Integer> result, List<List<Integer>> allResult) {
        if (sum == target&&result.size()>1) { //能满足这个 result.size()>1保证必须是2个数相加
            if(!allResult.contains(result))  //去掉重复的
                allResult.add(new ArrayList<>(result));
            return;
        } else  if (sum > target){ //超过的不行了 返回吧
            return;
        }
        //begin 确保每次后续的数只能等于或者大于之前的数
        for (int i = begin; i < set.length; i++) {
            result.add(set[i]); //加上的先试试 深度的话如果是可以抛弃之前的结果的话
            dfs(set, target, i,sum + set[i], result,allResult);
            result.remove(result.size()-1); //不要这个数试试
        }
    }

    /**
     * 每个元素只能使用一次的版本 主要是begin的区别
     * @param set
     * @param target
     * @param begin
     * @param sum
     * @param result
     * @param allResult
     */
    public void dfs1(int[] set, int target, int begin, int sum, List<Integer> result, List<List<Integer>> allResult) {
        if (sum == target&&result.size()>1) { //能满足这个 result.size()>1保证必须是2个数相加
            if(!allResult.contains(result))  //去掉重复的
               allResult.add(new ArrayList<>(result));
            return;
        } else  if (sum > target){ //超过的不行了 返回吧
            return;
        }
        //begin
        for (int i = begin; i < set.length; i++) {
            result.add(set[i]); //加上的先试试 深度的话如果是可以抛弃之前的结果的话
            dfs1(set, target, i+1,sum + set[i], result,allResult);
            result.remove(result.size()-1); //不要这个数试试
        }
    }

    /**
     * 每个元素只用一次的版本，且元素的个数有限制
     * @param set
     * @param target
     * @param begin
     * @param sum
     * @param step
     * @param result
     * @param allResult
     */
    public void dfs2(int[] set, int target,int times, int begin, int sum, int step, List<Integer> result, List<List<Integer>> allResult) {
        if (sum == target && step == times) {
            if (!allResult.contains(result)) {
                allResult.add(new ArrayList<>(result));
                return;
            }
        } else {
            if (step > 3) { //超过次数了，算了吧。
                return;
            }
            if (sum > target) { //和已经超过目标值，算了吧
                return;
            }
        }
        for (int i = begin; i < set.length; i++) {
            result.add(set[i]);
            dfs2(set, target, times,i + 1, sum + set[i], step + 1, result, allResult);
            result.remove(result.size() - 1);
        }
    }
}

package com.hong.py.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * author: hongpy
 * create time: 2020-06-27 11:04
 * description:
 * life for code
 */
public class RestoreIPAddresses {

    public static void main(String[] args) {
        RestoreIPAddresses restoreIPAddresses = new RestoreIPAddresses();
        restoreIPAddresses.restoreIPAddresses("25525511135"); //解析ip地址
        restoreIPAddresses.restoreIPAddresses("010010");
    }


    public void restoreIPAddresses(String str) {
        List<String> result = new ArrayList<>();
        dfs(str, 0, "", result,0);

        for (String s : result) {
            System.out.print("[");
            System.out.print(s.substring(1)); //去掉第一个,
            System.out.println("]");
        }
    }

    /**
     *
     * @param str
     * @param begin 开始分割点
     * @param ip
     * @param result
     * @param step  分割的次数
     */
    public void dfs(String str, int begin,String ip,List<String> result,int step) {
        if (begin == str.length()&&step==4) { //分割完了 且步长必须为4
            result.add(ip);
            return;
        } else {
            if (step >= 4) {
                return;
            }
        }

        for (int i = begin+1; i <=str.length(); i++) {
            String substring = str.substring(begin, i); //subtring是前闭后开的
            long i1 = Long.parseLong(substring);
            if (substring.startsWith("0")&&substring.length()>1) continue; //不能有 以0开头的多位
            if (i1 <=255) { //是可以的
                dfs(str, i, ip + "." + substring, result,step+1);
            }
        }
    }
}

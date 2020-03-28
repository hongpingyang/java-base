package com.hong.py.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 示例:
 *
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 *
 *Java split()用法
 * 特殊情况有 * ^ : | . \  符号在正则表达式中属于一种有特殊含义的字符，如果使用此种字符作为分隔符，必须使用转义符即\\加以转义。
 */
public class Day3 {


    public static void main(String[] args) {
        Day3 day3 = new Day3();

        List<String> strings = day3.restoreIpAddresses("010010");

        for (String str :
                strings) {
            System.out.println(str);
        }

    }


    private List<String> addresslist;
    public List<String> restoreIpAddresses(String s) {
        addresslist = new ArrayList<>();
        doAction(s);
        return addresslist;
    }


    public void doAction(String s) {

        String[] split = s.split("\\.");
        if (split.length == 4) { //分配完成了
            if (split[3].length()>3) return;
            if (Integer.valueOf(split[3]) > 255) {
                return;
            }
            if(split[3].length()>=2 &&split[3].charAt(0)=='0') return;
            addresslist.add(split[0]+"."+split[1]+"."+split[2]+"."+split[3]) ;
            return;
        }
        int i = s.lastIndexOf(".");
        String subtail=(i==-1?"":s.substring(0,i));
        //if(subtail.length()>=2 &&subtail.charAt(0)=='0') return;
        String s1 = (i==-1?s:split[split.length - 1]);
        if (s1.length() >= 3) {
            for (int j = 1; j <=3 ; j++) {
                if(s1.substring(0, j).equals("")||s1.substring(j).equals("")) continue;
                if(s1.substring(0, j).length()>=2 &&s1.substring(0, j).charAt(0)=='0') continue;
                //if(s1.substring(j).length()>=2 &&s1.substring(j).charAt(0)=='0') continue;
                String newstr = subtail + (subtail==""?"":".") + s1.substring(0, j)+"."+s1.substring(j);
                if (j == 3) {
                    String substring = s1.substring(0, j);
                    if (Integer.valueOf(substring) > 255) {
                        continue;
                    }
                }
                doAction(newstr);
            }
        }
        else
        {
            for (int j = 1; j <=s1.length() ; j++) {
                if(s1.substring(0, j).equals("")||s1.substring(j).equals("")) continue;
                if(s1.substring(0, j).length()>=2 &&s1.substring(0, j).charAt(0)=='0') continue;
                String newstr = subtail +  (subtail==""?"":".") + s1.substring(0, j)+"."+s1.substring(j);
                doAction(newstr);
            }
        }
    }
}

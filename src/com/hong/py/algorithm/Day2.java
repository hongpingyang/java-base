package com.hong.py.algorithm;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 */
public class Day2 {
    public boolean match(String s, String p) {
        char[] chars = s.toCharArray();
        char[] charp = p.toCharArray();
        int slength = chars.length;
        int plength = charp.length;

        //做个快速检测
        for (int i = 0; i < plength; i++) {
            if(charp[i]!='.'&&charp[i]!='*'&&(i+1==plength||charp[i+1]!='*'))
            {
                if(!s.contains(String.valueOf(charp[i])))
                    return false;
            }
        }
        
        if (p.contains("*")) {
            for (int i = 0; i < plength; i++) {
                if(charp[i]=='.')
                {
                    if(i + 1 < plength&&charp[i+1]=='*')
                    {
                        StringBuilder b = new StringBuilder();
                        for (int j = 0; j <= slength; j++) {
                            if(j!=0)
                              b.append('.');
                            String newstr = p.substring(0,i)+b + p.substring(i +2); //把*也要替换掉
                            if(match(s,newstr))
                                return true;
                        }
                    }else
                    {
                        continue;
                    }
                }
                else
                {
                    if(i + 1 < plength&&charp[i+1]=='*')
                    {
                        StringBuilder b = new StringBuilder();
                        for (int j = 0; j <= slength; j++) {
                            if(j!=0)
                                b.append(charp[i]);
                            String newstr = p.substring(0,i)+ b + p.substring(i +2); //把*也要替换掉
                            if(match(s,newstr))
                                return true;
                        }
                    }else
                    {
                        
                        continue;
                    }
                }
            }
            return false;
        }
        else
        {
            if (slength == plength ) {
                boolean isfalse=false;
                for (int i = 0; i < slength; i++) {
                    if(chars[i]==charp[i]||charp[i]=='.') //相等的条件
                    {

                    }
                    else
                    {
                        isfalse=true;
                        break;
                    }
                }
                return  isfalse==false;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Day2 days = new Day2();
        //System.out.println(days.match("aab", "c*a*b"));
        //System.out.println(days.match("aa", "a"));
        //System.out.println(days.match("aa", "a*"));
        //System.out.println(days.match("", ".*"));
        System.out.println(days.match("mississippi", "mis*is*ip*."));
    }
}

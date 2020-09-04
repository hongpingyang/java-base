package com.hong.py.algorithm.dtgh;

/**
 * author: hongpy
 * create time: 2020-06-30 14:08
 * description:
 * life for code
 */
public class InterleavingString {

    /**
     * 给出三个字符串:s1、s2、s3，判断s3是否由s1和s2交叉构成。
     *
     * 样例
     * 比如 s1 = "aabcc" s2 = "dbbca"
     *  当 s3 = "aadbbcbcac"，返回  true.
     * 当 s3 = "aadbbbaccc"， 返回 false.
     * @param args
     */
    public static void main(String[] args) {
        InterleavingString interleavingString = new InterleavingString();
        interleavingString.interleavingString("aabcc", "dbbca", "aadbbcbcac");
        interleavingString.dfs("aabcc", "dbbca", "aadbbbaccc", 0, 0);
    }


    public void interleavingString(String s1, String s2, String s3) {

        //想象一下一个二维数组，
        boolean[][] f = new boolean[s1.length()+1][s2.length()+1];
        f[0][0]=true;
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <=s2.length(); j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                boolean b1=false;
                boolean b2=false;
                if (i-1>=0&&s1.substring(i-1, i).equals(s3.substring(i + j-1, i + j ))) { //s1能满足
                    b1=true;
                }

                if (j-1>=0&&s2.substring(j-1, j).equals(s3.substring(i + j-1, i + j))) { //s2能满足
                    b2=true;
                }
                //上方和左方向相邻点的值
                boolean i1=true;
                boolean j1=true;
                if (i - 1 >= 0) {
                    i1=f[i - 1][j];
                }
                if (j - 1 >= 0) {
                    j1=f[i][j-1];
                }
                //看 s3 前i+j 字符能否满足 要看 i+j-1 字符的情况，而i+j-1 分为两种情况，只要一条路能走通久行。
                f[i][j] = ((b1&i1) || (b2&j1));
            }

        }

        System.out.println(f[s1.length()][s2.length()]);
    }


    /**
     * 递归解法
     * @param s1
     * @param s2
     * @param s3
     * @param begin1
     * @param begin2
     */
    public void dfs(String s1, String s2, String s3, int begin1, int begin2) {
        //System.out.println(begin1);
        System.out.println(begin2);
        if (begin1 + begin2 == s3.length()) {
            System.out.println("能走到");
           return;
        }

        //这个s1能满足 先s1一条道走到黑试试
        if (s1.length()>begin1&&s1.substring(begin1, begin1 + 1).equals(s3.substring(begin1 + begin2, begin1 + begin2 + 1))) {
            dfs(s1, s2, s3, begin1 + 1, begin2);
            //回退一个让s2试试
            if (s2.length()>begin2&&s2.substring(begin2, begin2 + 1).equals(s3.substring(begin1 + begin2, begin1 + begin2 + 1))) {
                 dfs(s1, s2, s3, begin1-1, begin2+1);
            }
        }
        else {
            //这个s2也能满足 s2一条道走到黑试试
            if (s2.length() > begin2 && s2.substring(begin2, begin2 + 1).equals(s3.substring(begin1 + begin2, begin1 + begin2 + 1))) {
                dfs(s1, s2, s3, begin1, begin2 + 1);
            }
        }
        return;
    }
}

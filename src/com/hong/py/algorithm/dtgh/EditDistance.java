package com.hong.py.algorithm.dtgh;

import java.util.Scanner;

/**
 * author: hongpy
 * create time: 2020-06-30 16:14
 * description:
 * life for code
 */
public class EditDistance {


    public static void main1(String[] args){
        Scanner sc=new Scanner(System.in);

        while(sc.hasNext()){
            String str1=sc.nextLine();
            //sc.nextLine();
            String str2=sc.nextLine();

            char[] strarry1=str1.toCharArray();
            char[] strarry2=str2.toCharArray();

            int length1=str1.length();
            int length2=str2.length();
            int[][] result=new int[length1][length2];

            for(int x=0;x<length1;x++){
                for(int y=0;y<length2;y++){
                    if(x==0||y==0){
                        result[x][y]=0;
                    }
                    else {
                        if(strarry1[x]==strarry2[y]){
                            result[x][y]=result[x-1][y-1];
                        }else{
                            int va1=result[x-1][y]+1;
                            int va2=result[x][y-1]+1;
                            int va3=result[x-1][y-1]+1;
                            result[x][y]=Math.min(Math.min(va1,va2),va3);
                        }
                    }
                }
            }

            int editdistance=result[length1-1][length2-1];
            System.out.println("1/"+editdistance);
        }
    }

    public static void main2(String[] args) {
        EditDistance editDistance = new EditDistance();
        editDistance.EditDistance("xyz", "xxd");
    }

    public void EditDistance(String s1, String s2) {
        //想象一下一个二维数组，
        int[][] f = new int[s1.length()+1][s2.length()+1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0) { //初始化边缘
                    f[i][j] = 0;
                    continue;
                } else if (s1.substring(i-1, i).equals(s2.substring(j-1, j))) { //如果相等
                    f[i][j] = f[i - 1][j - 1];  //斜对角
                } else {

                    int temp1 = f[i - 1][j - 1];//替换成j
                    int temp2 = f[i][j - 1];//后面加一个j
                    int temp3 = f[i-1][j];//删除 j
                    f[i][j]=Math.min(Math.min(temp1,temp2),temp3)+1;
                }
            }
        }
        System.out.println("最大编辑距离："+f[s1.length()][s2.length()]);
    }


    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()) {
            //scanner.nextLine();
            String input = scanner.next();
            int num = Integer.parseInt(input);
            int countof1bit = 0;
            boolean last1 = false;
            int maxcount = 0;
            for (int i = 0; i < 32; i++) {
                if ((num & 1) == 1) {
                    countof1bit++;
                    last1 = true;
                } else {
                    if (countof1bit > maxcount) {
                        maxcount = countof1bit;
                    }
                    countof1bit = 0;
                    last1 = false;
                }
                num = num >> 1;
            }

            if (countof1bit > maxcount) {
                maxcount = countof1bit;
            }

            System.out.println(maxcount);
        }


    }

}

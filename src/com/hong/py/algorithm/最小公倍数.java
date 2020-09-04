package com.hong.py.algorithm;

import java.util.Scanner;

/**
 * author: hongpy
 * create time: 2020-07-05 14:59
 * description:
 * life for code
 */
public class 最小公倍数 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double valu=scanner.nextDouble();
        double cubeRoot = getCubeRoot(valu);

        System.out.println(String.format("%.1f", cubeRoot));

        /*Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        int x = scanner.nextInt();
        最小公倍数 z = new 最小公倍数();
        int gbs = z.gbs(i, x);
        System.out.println(gbs);*/
    }



    public static double getCubeRoot(double input) {

        for(double i=0; i< input/2; i+=0.001){
            if(i*i*i==input) return i;
            if (i * i * i - 0.01 <= input &&  i * i * i + 0.01 >= input) {
                return i;
            }
        }
        return 0;
    }


    //  23663778
    public int gbs(int i, int x) {


        if (i < x) {
            int result=x;
            for (int j = 1; j < i * x;) {
                if (result % i == 0) {
                    return result;
                } else {
                    j++;
                    result=j*x;
                }
            }
        } else {
            int result=i;
            for (int j = 1; j < i * x;) {
                if (result % x== 0) {
                    return result;
                }else {
                    j++;
                    result=j*i;
                }
            }
        }

        return i * x;
    }
}

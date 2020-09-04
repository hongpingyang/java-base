package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-27 21:47
 * description:
 * life for code
 */
public class PowXN {

    public static void main(String[] args) {
        PowXN powXN = new PowXN();
        double v = powXN.powXn(3, 10);

        //       10
        //    5      5
        //  2 2 1  2 2 1
        System.out.println(v);
    }


    /**
     * 求解x的n次方
     * @param x
     * @param n
     */
    public double powXn(int x, int n) {

        if(n<0) return 1.0 / pow(x, -1 * n);
        else return pow(x, n);
    }

    public double pow(int x, int n) {
        if (n == 0) return 1; //
        double pow = pow(x, n / 2);
        if (n % 2 == 0) {  //只有2种可能行，0,或者 1
            return pow * pow;
        } else {
            return pow * pow*x;
        }
    }

}

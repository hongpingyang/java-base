package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-27 21:57
 * description:
 * life for code
 */
public class SqrtX {

    public static void main(String[] args) {
        SqrtX sqrtX = new SqrtX();
        sqrtX.sqrtX(25);

    }


    public void sqrtX(int x) {
        if (x <= 1) {
            System.out.println(1);
        }
        double sqrtx = sqrtx(x, 0, x / 2);
        System.out.println((sqrtx));
    }

    /**
     * 二分查找法。不断逼近
     * @param x
     * @param begin
     * @param end
     * @return
     */
    public double sqrtx(int x, double begin, double end) {
        if (begin < end) {
            double mid = (begin + end) / 2;
            if (mid * mid==x || ((mid * mid + 0.00001) >= x && (mid * mid - 0.00001)<=x)) {
                return mid;
            } else {
                if (mid * mid > x) {
                    return sqrtx(x, begin, mid);
                } else {
                    return sqrtx(x, mid, end);
                }
            }
        } else {
            return -1;
        }
    }
}

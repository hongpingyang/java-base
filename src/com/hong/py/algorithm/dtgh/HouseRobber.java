package com.hong.py.algorithm.dtgh;

/**
 * author: hongpy
 * create time: 2020-06-30 22:09
 * description:
 * life for code
 */
public class HouseRobber {

    public static void main(String[] args) {
        HouseRobber houseRobber = new HouseRobber();
        houseRobber.houseRobber(new int[]{2,7,9,3,1});
    }


    public void houseRobber(int[] robbers) {
        int n = robbers.length;
        int[] f = new int[n];
        int maxglobal=0;

        for (int i = 0; i < n; i++) {
            if (i == 0||i==1) {
                f[i] = robbers[i];
            }
            else {

                f[i] = Math.max(f[i - 1], f[i - 2] + robbers[i]);

            }

            if (maxglobal < f[i]) {
                maxglobal = f[i];
            }
        }
        System.out.println(maxglobal);
    }
}

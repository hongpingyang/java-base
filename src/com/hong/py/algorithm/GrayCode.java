package com.hong.py.algorithm;

import java.util.ArrayList;

/**
 * author: hongpy
 * create time: 2020-06-17 09:29
 * description:
 * life for code
 */
public class GrayCode {

    public static void main(String[] args) {
        GrayCode code = new GrayCode();
        code.grayCode(3);
    }

    public void grayCode(int num) {

        double numcount = 1<<num;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <numcount ; i++) {
            list.add(i ^ (i >> 1));  //生成格雷码的公式
        }
        for (Integer integer : list) {
            printBinary(integer,num);
        }
    }

    /**
     * 打印二进制编码
     * @param integer
     */
    public void printBinary(int integer,int total) {

        StringBuffer result = new StringBuffer();
        for (int i = 0; i <total; i++) {
            if ((integer & 1) == 1) { //表明这一位为1
                result.insert(0, "1");
            } else {                  //表明是0
                result.insert(0, "0");
            }
            integer=integer>>>1;
        }
        System.out.println(result);
    }
}

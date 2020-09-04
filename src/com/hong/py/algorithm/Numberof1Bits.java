package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-17 09:05
 * description:
 * life for code
 */
public class Numberof1Bits {

    public static void main(String[] args) {
        Numberof1Bits numberof1Bits = new Numberof1Bits();
        numberof1Bits.printBinary(-11);
        numberof1Bits.numberof1Bits(-11);

        numberof1Bits.printBinary(11);
        numberof1Bits.numberof1Bits(11);
    }

    /**
     * 打印二进制编码
     * @param integer
     */
    public void printBinary(int integer) {

        StringBuffer result = new StringBuffer();
        System.out.println("================");
        for (int i = 0; i <32; i++) {
            if ((integer & 1) == 1) { //表明这一位为1
                result.insert(0, "1");
            } else {                  //表明是0
                result.insert(0, "0");
            }
            integer=integer>>>1;
        }
        System.out.println(result);
    }

    public void numberof1Bits(int num) {
        int count=0;
        for (int i = 0; i < 32; i++) {
            if ((num & 1) == 1) {
                count++;
            }
            num=num>>>1;
        }
        System.out.println(count);
    }
}

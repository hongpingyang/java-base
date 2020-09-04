package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-16 23:11
 * description:
 * life for code
 */
public class ReverseNum {

    public static void main(String[] args) {
        ReverseNum reverseNum = new ReverseNum();
        reverseNum.reverseNum(43261596);
        reverseNum.binaryReverseNum(43261596);
        reverseNum.printBinary(43261596);
    }

    /**
     * 回文
     * @param integer
     */
    public void reverseNum(Integer integer) {
        Integer reverseNum=0;
        char[] chars = integer.toString().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            reverseNum += (int) (Integer.valueOf(String.valueOf(chars[i])) * (Math.pow(10, i)));
        }
        /*for (int i = 0; i < integer.toString().length(); i++) {
            reverseNum += (int)(Integer.valueOf(integer.toString().substring(i,i+1)) * (Math.pow(10, i)));
        }*/

        System.out.println(reverseNum);
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
            integer=integer>>1;
        }
        System.out.println(result);
    }

    /**
     * 二进制回文
     * @param integer
     */
    public void binaryReverseNum(int integer) {
        int result=0;
        for (int i = 0; i < 32; i++) {
            if ((integer & 1) == 1) { //表明这一位为1
                result = (result << 1) + 1;
                //result |= 1<< (31-i);  //相应的结果的前面也要置为1
            } else {                  //表明是0
                result = (result << 1);
                //result &= ~(1<< (31-i));  //相应的结果的前面也要置为0
            }
            integer=integer>>1;
        }
        System.out.println(result);
    }
}

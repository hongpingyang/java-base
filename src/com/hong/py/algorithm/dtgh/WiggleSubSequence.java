package com.hong.py.algorithm.dtgh;

import java.util.Arrays;

/**
 * author: hongpy
 * create time: 2020-06-29 11:50
 * description:
 * life for code
 */
public class WiggleSubSequence {

    public static void main(String[] args) {
        WiggleSubSequence wiggleSubSequence = new WiggleSubSequence();
        wiggleSubSequence.wiggleSubSequence(new int[]{0,1,7,4,9,2,5,4,6});
    }


    private int[] result;

    public void wiggleSubSequence(int[] datas) {
        int length = datas.length;
        result = new int[length + 1];
        Arrays.fill(result, 1);
        result[0] = 1;
        result[1] = 2;
        int maxindex=0;
        int maxglobal=0;
        for (int i = 2; i < length; i++) {
            if ((datas[i - 2] < datas[i - 1]&&datas[i] < datas[i - 1]) //满足条件
                || (datas[i - 2] > datas[i - 1] && datas[i] > datas[i - 1])) {
                result[i]=result[i-1]+1;
            }else{
                result[i]+=1; //与前面的一个能形成一个
            }
            if (result[i] > maxglobal) {
                maxglobal = result[i];
                maxindex = i;
            }
        }

        //就是相邻的元素得一升一降
        System.out.println("最大WiggleSubSequence长度为：" + maxglobal);

        System.out.print("[");
        StringBuffer stringBuffer=new StringBuffer();
        //倒推起始点
        for (int i = maxindex; i >(maxindex-maxglobal); i--) {
            stringBuffer.insert(0,datas[i] + ",");
        }
        System.out.print(stringBuffer.substring(0,stringBuffer.length()-1));
        System.out.println("]");
    }


}

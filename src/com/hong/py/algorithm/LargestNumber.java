package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-20 09:47
 * description:
 * life for code
 */
public class LargestNumber {

    /**
     * 找出数组所有数字能组成的最大数
     * @param args
     */
    public static void main(String[] args) {
        LargestNumber largestNumber = new LargestNumber();
        largestNumber.largestNumber(new int[]{3,30,34,5,9});
    }


    public void largestNumber(int[] datas) {

        StringBuffer result = new StringBuffer();
        for (int i = 0; i < datas.length; i++) {
            heapSort(datas, datas.length-i); //堆排序。找出最大的那个数
            result.append(datas[datas.length-1-i]);
        }
        System.out.println(result);
    }

    public void heapSort(int[] datas,int end) {

        //int length = datas.length;

        // 数字比较大小，两两相加，看哪种情况大

        for (int i = end / 2 - 1; i >= 0; i--) {

            int leftSon = 2 * i + 1;
            int rightSon=leftSon+1;
            String maxson = String.valueOf(datas[leftSon]);
            int maxindex=leftSon;
            if (rightSon <= end - 1) {
                String value1=maxson+datas[rightSon];
                String value2=datas[rightSon]+maxson;
                if (value1.compareTo(value2) < 0) {
                    maxson=String.valueOf(datas[rightSon]);
                    maxindex=rightSon;
                }
            }
            String value1 = datas[i]+maxson;
            String value2 = maxson+datas[i];
            if (value2.compareTo(value1) > 0) {
                //交换位置
                int temp = datas[i];
                datas[i]=datas[maxindex];
                datas[maxindex]=temp;
            }
        }

        //datas[0]是最大的位置
        //与最后一位交换
        int temp=datas[0];
        datas[0] = datas[end - 1];
        datas[end-1]=temp;

    }
}

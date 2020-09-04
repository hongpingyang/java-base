package com.hong.py.algorithm.dtgh;

/**
 * author: hongpy
 * create time: 2020-06-28 20:37
 * description:
 * life for code
 * //最大连续子序列和
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        MaximumSubarray maximumSubarray = new MaximumSubarray();
        maximumSubarray.maximumSubarray(new int[]{ -2,1,-3,4,-1,2,1,-5,4});
        maximumSubarray.maximumjiSubarray(new int[]{ 2,3,-2,4});
        maximumSubarray.maximumjiSubarray(new int[]{ -2,1,-3,4,-1,2,1,5,4});
    }


    private static int sum;
    private static int maxsum;
    private static int maxbeginindex;
    private static int maxendindex;
    public void maximumSubarray(int[] datas) {

        for (int i = 0; i < datas.length; i++) {
            subMaxSumArray(datas[i],i);
        }

        System.out.print("[");
        StringBuffer stringBuffer=new StringBuffer();
        for (int i = 0; i < datas.length; i++) {
            if (i >= maxbeginindex&&i<=maxendindex) {
                stringBuffer.append(datas[i] + ",");
            }
        }
        System.out.print(stringBuffer.substring(0,stringBuffer.length()-1));
        System.out.println("]");
        System.out.println("最大和为："+maxsum);
    }

    /**
     * 最大连续子序列和
     * @param data
     * @param i
     */
    public void subMaxSumArray(int data, int i) {
        if (sum <= 0) {//没有积极的意义。抛弃之前的
            sum = data;
            maxsum=data;
            maxbeginindex=i;
        } else { //有积极的意义，加入
            sum += data;
            if (maxsum < sum) {
                maxsum=sum;
                maxendindex=i;
            }
        }
    }



    private static double ji=1;
    private static double minji=1;
    private static double maxji=Integer.MIN_VALUE;
    private static int minbeginindex;
    public void maximumjiSubarray(int[] datas) {
        ji=1;
        minji=1;
        maxji=1;
        maxbeginindex=0;
        minbeginindex=0;
        maxendindex=0;

        for (int i = 0; i < datas.length; i++) {
            subMaxJiArray(datas[i],i);
        }

        System.out.print("[");
        StringBuffer stringBuffer=new StringBuffer();
        double maxvalue=maxji;
        //倒推起始点
        for (int i = maxendindex; i >=0; i--) {
            stringBuffer.insert(0,datas[i] + ",");
            if (maxvalue == datas[i]) {
                break;
            }
            maxvalue /= datas[i];
        }
        System.out.print(stringBuffer.substring(0,stringBuffer.length()-1));
        System.out.println("]");
        System.out.println("最大积为："+maxji);
    }

    /**
     * 最大连续子序列积  负负得正
     * @param data
     * @param i
     */
    public void subMaxJiArray(int data, int i) {

        // 方法1：计算队列种负数的个数，为偶数个，那整个队列为最大值
        //为奇数个，从后往前遍历，找到第一个奇数，之前的队列为最大的，错！不一定啊，后面的队列有可能也是很大的


        //方法2： 队列种存在小于1 的小数方法1不合适

        double temp=ji;
        ji = Math.max(Math.max(data, data * ji), data * minji);
        //每次记录下最小值，这个当遇到一个负数的时候会有可能变最大值。
        minji = Math.min(Math.min(data, data * temp), data * minji);
        if (maxji < ji) {
            maxji=ji;
            maxendindex=i;
        }
    }
}

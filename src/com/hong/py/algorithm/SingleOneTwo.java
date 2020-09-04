package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-17 10:18
 * description:
 * life for code
 */
public class SingleOneTwo {


    public static void main(String[] args) {
        SingleOneTwo singleOneTwo = new SingleOneTwo();
        int[] datas={1,2,3,5,3,2,1};
        Integer integer = singleOneTwo.singleOneTwo(datas);
        System.out.println(integer);


        int[] dataTrees={11,62,3,62,11,3,542,3,62,11};
        Integer integer1 = singleOneTwo.singleOneTree(dataTrees);
        System.out.println(integer1);

        int[] dataForTwo={11,62,3,542,3,62,542};
        singleOneTwo.singleOneForTwo(dataForTwo);
    }

    /**
     * 给定一个数组，里面有一个元素是单个，其它的都是偶数个,请找出这个单个的元素
     */
    public Integer singleOneTwo(int[] datas) {
        int x=0;
        for (int value : datas) {
            x = x ^ value;  //异或后相等的都变为0了
        }
        return x;
    }

    /**
     * 给定一个数组，里面有一个元素是单个，其它的都是3个,请找出这个单个的元素
     * @param datas
     * @return
     */
    public Integer singleOneTree(int[] datas) {
        //用一个32位的bit来表示 数组上所有的数据
        int[] count = new int[Integer.SIZE]; //表示在i为出现1的的次数
        for (int value : datas) {
            for (int i = 0; i < Integer.SIZE; i++) {
                if (((value >> i) & 1) == 1) { //表明这个数的第i位为1，
                    count[i]++;
                }
                //有3次了置为0 消除掉出现3次数的影响
                if (count[i] == 3) {
                    count[i]=0;
                }
            }
        }

        int result=0;
        for (int i=0;i<Integer.SIZE;i++) {
            if (count[i] == 1) {
                result+=(1<<(i));
            }
        }
        return result;
    }

    /**
     * 给定一个数组，里面有2个个元素是单个，其它的都是偶数个,请找出这2个单个的元素
     * @param datas
     * @return
     */
    public Integer singleOneForTwo(int[] datas) {

        int x=0;
        for (int value : datas) {
            x = x ^ value;  //异或后相等的都变为0了
        }

        //这个x是2个单个元素异或后的结果



        for (int i = 0; i < Integer.SIZE; i++) {
            if (((x >>> 1) & 1) == 1) {

            }
        }



        return x;
    }
}

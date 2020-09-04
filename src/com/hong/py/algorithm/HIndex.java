package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-19 10:19
 * description:
 * life for code
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
 *
 * h 指数的定义: “一位有 h 指数的学者，代表他（她）的 N 篇论文中至多有 h 篇论文，分别被引用了至少 h 次，其余的 N – h 篇论文每篇被引用次数不多于 h 次。”
 *
 * citations = [3,0,6,1,5]
 * 输出: 3
 * 解释: 给定数组表示研究者总共有 5篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。由于研究者有 3 篇论文每篇至少被引用了3次，其余两篇论文每篇被引用不多于3次，所以她的 h 指数是3。
 */
public class HIndex {

    public static void main(String[] args) {
        HIndex index = new HIndex();
        index.hIndex(new int[]{3,9,6,1,5,5,6});

    }


    public void hIndex(int[] datas) {

        int maxHindex=0;
        int count=0;
        for (int i = 1; i <=datas.length; i++) {//遍历的次数

            count=0;
            for (int j = 0; j <datas.length ; j++) { //判断是否满足条件
                if (datas[j] >= i) {
                    count++;
                }
            }
            //表明这个一个H指数
            if (count >= i) {
                //要取论文数和引用数的最小值
                maxHindex = Math.min(count, i);
                continue;
            }
        }

        System.out.println(maxHindex);
    }
}

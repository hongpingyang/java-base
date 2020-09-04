package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-07-10 14:16
 * description:
 * life for code
 */
public class 二叉树的锯齿形层次遍历解析 {

    public static void main(String[] args){
        print(new String[]{"3","9","20","null","null","15","7"});
    }

    public static void print(String[] datas){
        int length=datas.length;
        int lastleft=0; //每一层最左的一个节点
        int lastright=0; //每一层最右的一个节点
        System.out.println("[");
        System.out.print("["+datas[0]+"");
        for(int i=1;i<length;i++){
            if(2*lastleft+1==i){ //到达下一层了
                lastleft=i;
                  System.out.println("],");
                  System.out.print("[");
                  if(datas[i].equals("null")) continue;
                  System.out.print(datas[i]+",");
            } else{
                  if(2*(lastright+1)==i){
                      lastright=i;
                      if(datas[i].equals("null")) continue;
                      System.out.print(datas[i]+"");
                  }else{
                      if(datas[i].equals("null")) continue;
                      System.out.print(datas[i]+",");
                  }
            }
        }

        System.out.println("]");
        System.out.println("]");
    }
}

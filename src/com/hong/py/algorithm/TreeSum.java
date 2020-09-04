package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-10 23:38
 * description:
 * life for code
 */
public class TreeSum {

    private static String[] treeInfo={"5","4","8","11","null","17","4","7","1","null","null","5","3"};
    private static int limit=22;
    private int val;
    private TreeSum left;
    private TreeSum right;


    public TreeSum(int val, TreeSum left, TreeSum right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }



    public static void main(String[] args) {
        String[] tree = new String[treeInfo.length];
        for (int i = 0; i < 13; i++) {
            if(treeInfo[i]=="null") continue;


            sum(treeInfo, 0, i,i,tree);

            //treeInfo[i] = "null";
        }

        System.out.println("================");
        for (int i = 0; i < 13; i++) {
            System.out.println(tree[i]);
        }

    }


    private static void sum(String[] data,int s, int n,int num,String[] result) {
        int length=data.length;
        if(data[n]=="null") return;
        int v = Integer.parseInt(data[n])+s;

        boolean hasleft=false;
        boolean hasright=false;
        //找左子节点
        int index=n+1;
        System.out.println(n);
        if (length > 2 * index - 1 && data[2 * index - 1] != "null") {
            sum(data,v, 2*index - 1,num,result);
            hasleft=true;
        }

        //找右子节点
        if (length > 2 * index && data[2 * index] != "null") {
            sum(data,v, 2*index,num,result);
            hasright=true;
        }

        if (!hasleft && !hasright) {
            System.out.println(num);
            if (v >= limit) { //有存在大于等于的
                result[num] = data[num];
            }
        }

        return;
    }
}

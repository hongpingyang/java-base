package com.hong.py.algorithm;

import com.hong.py.algorithm.pojo.MyListNode;
import com.hong.py.algorithm.pojo.TreeNode;

/**
 * author: hongpy
 * create time: 2020-08-02 20:49
 * description:
 * life for code
 */
public class 二叉树展开为链表 {

    private static TreeNode listNodeHead;
    private static TreeNode currentNodeHead;

    public static void main(String[] args) {

        TreeNode node1=new TreeNode(1);
        TreeNode node2=new TreeNode(2);
        TreeNode node3=new TreeNode(3);
        TreeNode node4=new TreeNode(4);
        TreeNode node5=new TreeNode(5);
        TreeNode node6=new TreeNode(6);
        node1.setLeft(node2);
        node1.setRight(node5);
        node2.setLeft(node3);
        node2.setRight(node4);
        node5.setRight(node6);
        flatten(node1);

        //打印
        currentNodeHead=listNodeHead;
        System.out.println(currentNodeHead.getVal());
        while(currentNodeHead.getRight()!=null){
            System.out.println(currentNodeHead.getRight().getVal());
            currentNodeHead=currentNodeHead.getRight();
        }


        flatten1(node1);
        //打印
        currentNodeHead=node1;
        System.out.println(currentNodeHead.getVal());
        while(currentNodeHead.getRight()!=null){
            System.out.println(currentNodeHead.getRight().getVal());
            currentNodeHead=currentNodeHead.getRight();
        }
    }

    public static void flatten(TreeNode root) {
       if(root!=null){
           if(listNodeHead==null){
               listNodeHead=new TreeNode(root.getVal());
               currentNodeHead=listNodeHead;
           }
           else {
               TreeNode node=new TreeNode(root.getVal());
               currentNodeHead.setRight(node);
               currentNodeHead=node;
           }
       }
       else{
           return;
       }
       flatten(root.getLeft());
       flatten(root.getRight());
    }

    public static void flatten1(TreeNode root) {
        if(root==null) return;
        flatten1(root.getRight());
        flatten1(root.getLeft());
        root.setRight(currentNodeHead);
        root.setLeft(null);
        currentNodeHead=root;
    }

}

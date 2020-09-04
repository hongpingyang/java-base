package com.hong.py.algorithm;

import javax.swing.tree.TreeNode;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.algorithm
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/3/30 12:16
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2020/3/30 12:16
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * /**
 *  * Definition for a binary tree nextNode.
 *  * public class TreeNode {
 *  *     int val;
 *  *     TreeNode left;
 *  *     TreeNode right;
 *  *     TreeNode(int x) { val = x; }
 *  * }
 *  *
 *
 *  输入：[8,3,10,1,6,null,14,null,null,4,7,13]
 * 输出：7
 *
 **/
public class Day4 {

    private  int val=Integer.MIN_VALUE;
    private  int rootval=Integer.MIN_VALUE;
    public int maxAncestorDiff(TreeNode root) {
        rootval=root.val;
        deep(root);
        return val;
    }

    public void deep(TreeNode treeNode ) {

       if(treeNode!=null)
       {
           int value=Math.abs(rootval-treeNode.val);
           if(value>val)
               val=value;
       }

       if(treeNode.left==null&&treeNode.right==null)
       {
           int value=Math.abs(rootval-treeNode.val);
           if(value>val)
               val=value;
           return;
       }
        if(treeNode.left!=null)
            deep(treeNode.left);
        if(treeNode.right!=null)
            deep(treeNode.right);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
     }
}

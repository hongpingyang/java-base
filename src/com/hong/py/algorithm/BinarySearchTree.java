package com.hong.py.algorithm;

import java.util.Scanner;

/**
 * author: hongpy
 * create time: 2020-06-15 20:07
 * description:
 * life for code
 */
public class BinarySearchTree {

    private int value;
    private BinarySearchTree parent;
    private BinarySearchTree left;
    private BinarySearchTree right;



    public BinarySearchTree(int value) {
        this.value = value;
        this.parent = null;
        this.left=null;
        this.right = null;
    }


    /**
     * 插入
     * @param data
     * @param tree
     */
    public void insert(int data, BinarySearchTree tree) {

        if (tree.left == null && tree.value > data) {
            BinarySearchTree tree1 = new BinarySearchTree(data);
            tree1.parent=tree;
            tree.left=tree1;
        }
        else if (tree.right == null && tree.value <= data) {
            BinarySearchTree tree1 = new BinarySearchTree(data);
            tree1.parent=tree;
            tree.right=tree1;
        }
        else {
            if (tree.right != null && tree.value <= data) {
                insert(data, tree.right);
            } else if (tree.left != null && tree.value > data) {
                insert(data, tree.left);
            }
        }
    }

    /**
     * 查找
     * @param data
     * @param tree
     */
    public void find(int data, BinarySearchTree tree) {
        if (data == tree.value) {
            System.out.println("找到了");
            return ;
        } else if (data < tree.value) {
            if (tree.left == null) {
                System.out.println("找不到结果");
                return;
            }
            find(data, tree.left);
        } else if (data > tree.value) {
            if (tree.right == null) {
                System.out.println("找不到结果");
                return;
            }
            find(data,tree.right);
        }
    }

    /**
     * 查找并返回结果
     * @param data
     * @param tree
     * @return
     */
    public BinarySearchTree findResult(int data, BinarySearchTree tree) {
        BinarySearchTree result=null;
        if (data == tree.value) {
            System.out.println("找到了");
            return tree;
        } else if (data < tree.value) {
            if (tree.left == null) {
                System.out.println("找不到结果");
                return null;
            }
            result=findResult(data, tree.left);
        } else if (data > tree.value) {
            if (tree.right == null) {
                System.out.println("找不到结果");
                return null;
            }
            result=findResult(data, tree.right);
        }
        return result;
    }

    /**
     * 找后继节点
     * @param root
     * @return
     */
    public BinarySearchTree findSuccessor(BinarySearchTree root) {
        if (root.right == null) {
            System.out.println("没有后继节点");
            return null;
        }

        BinarySearchTree cur=root.right;

        while (cur.left != null) {
            cur=cur.left;
        }

        return cur;
    }


    /**
     * 前序遍历
     */
    public void pre(BinarySearchTree root) {
        if (root != null) {
            System.out.println(root.value+" ");
            pre(root.left);
            pre(root.right);
        }
    }


    /**
     * 中序遍历
     */
    public void middle(BinarySearchTree root) {
        if (root != null) {
            middle(root.left);
            System.out.println(root.value+" ");
            middle(root.right);
        }
    }

    /**
     * 后序遍历
     */
    public void after(BinarySearchTree root) {
        if (root != null) {
            after(root.left);
            after(root.right);
            System.out.println(root.value+" ");
        }
    }


    /**
     * 删除
     * @param data
     * @param root
     */
    public BinarySearchTree delete(int data, BinarySearchTree root) {
        //先找到要删除的点
        BinarySearchTree result = findResult(data, root);
        if (result != null) {
            if (result.left == null && result.right == null) {
                if (result == root) {
                    root=null;
                    return root;
                }
                BinarySearchTree parent = result.parent;
                if (parent.left == result) {
                    parent.left = null;
                } else if (parent.right == result) {
                    parent.right = null;
                }
            } else if (result.left != null && result.right != null) {
                BinarySearchTree successor = findSuccessor(result);
                successor.left = result.left; // 删除的左边变为后继的左边
                result.left.parent = successor;//删除的左边的父节点变为后继的左边

                // 再来看后继节点的右边
                if (successor.right != null && successor.parent != result) { // 后继节点有右边,这其实就是下面情况3的第一种
                    //把后继的节点右边处理
                    successor.right.parent = successor.parent;
                    successor.parent.left = successor.right;

                    //把删除的点右边处理下
                    successor.right = result.right;
                    successor.right.parent = successor;
                } else if (successor.right == null) {    //如果后继节点没有右边,那其实就是情况1，没有左右子树
                    if (successor.parent != result) {        //如果后继节点的parent不等于删除的点 那么就需要把删除的右子树赋值给后继节点
                        successor.parent.left = null;        //注意原来的后继节点上的引用要删掉,否则会死循环

                        successor.right = result.right;
                        successor.right.parent = successor;
                    }
                }

                // 替换做完接下来就要删除节点了
                if (result == root) {
                    successor.parent = null;
                    root = successor;
                    return root;
                }

                successor.parent = result.parent;
                if (result.value < result.parent.value) {
                    result.parent.left = successor;
                } else {
                    result.parent.right = successor;
                }
            } else {
                if (result.right != null) {

                    if (result == root) {
                        root = result.right;
                        return root;
                    }

                    result.right.parent=result.parent;
                    if (result.value < result.parent.value) {
                        result.parent.left = result.right;
                    } else {
                        result.parent.right = result.right;
                    }
                }

                if (result.left != null) {

                    if (result == root) {
                        root = result.left;
                        return root;
                    }
                    result.left.parent=result.parent;
                    if (result.value < result.parent.value) {
                        result.parent.left = result.left;
                    } else {
                        result.parent.right = result.left;
                    }
                }
            }
        }
        return root;
    }

    // 用于获得树的层数
    public int getTreeDepth(BinarySearchTree root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    private void writeArray(BinarySearchTree currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null)
            return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.value);

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth)
            return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

    public void show(BinarySearchTree root) {
        if (root == null) {
            System.out.println("EMPTY!");
            return ;
        }
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        writeArray(root, 0, arrayWidth / 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line : res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }


    public static void main(String[] args) {

        Scanner cin = new Scanner(System.in);
        System.out.println("二叉搜索树假定不存重复的子节点,重复可用链表处理，请注意~~");
        System.out.println("请输入根节点:");
        int rootData = cin.nextInt();
        BinarySearchTree root = new BinarySearchTree(rootData);
        int t = 1;
        System.out.println("请输入第" + t + "个点:输入-1表示结束");
        while (true) { //
            int data = cin.nextInt();
            if (data == -1)
                break;
            root.insert(data,root);
            t++;
            System.out.println("请输入第" + t + "个点:输入-1表示结束");
        }
        root.show(root);		//找的别人写的打印二叉树形结构，感觉还不错，可以更加清晰
        System.out.println("删除测试:");
        while(true) {
            System.out.println("请输入要删除的点：-1表示结束");
            int key = cin.nextInt();
            root = root.delete(key,root);
            root.show(root);
            if(root == null) {
                System.out.println("树已经没有数据了~~");
                break;
            }
        }
    }




}

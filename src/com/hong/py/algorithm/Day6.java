package com.hong.py.algorithm;

import java.util.Arrays;

/**
 *
 * 给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。
 *
 * 每个节点都可能有下一个更大值（next larger value）：对于 node_i，如果其 next_larger(node_i) 是 node_j.val，那么就有 j > i 且  node_j.val > node_i.val，而 j 是可能的选项中最小的那个。如果不存在这样的 j，那么下一个更大值为 0 。
 *
 * 返回整数答案数组 answer，其中 answer[i] = next_larger(node_{i+1}) 。
 *
 * 注意：在下面的示例中，诸如 [2,1,5] 这样的输入（不是输出）是链表的序列化表示，其头节点的值为 2，第二个节点值为 1，第三个节点值为 5 。
 *
 * 示例 1：
 *
 * 输入：[2,1,5]
 * 输出：[5,5,0]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-node-in-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Day6 {




    public static void main(String[] args) {
        ListNode head1=new ListNode(2);
        ListNode head2=new ListNode(1);
        ListNode head3=new ListNode(5);
        head1.next=head2;
        head2.next=head3;
        int[] ints = nextLargerNodes(head1);

        for (int ins : ints) {
            System.out.println(ins);
        }
    }


    public static  int[] nextLargerNodes(ListNode head) {

        int[] arrayVal = new int[1000];
        ListNode currenthead;
        int index = 0;
        while (head!= null) {

            currenthead = head;

            if(currenthead.next==null)
            {
                arrayVal[index++] = 0;
            }

            while (currenthead.next != null) {
                if (head.val < currenthead.next.val) {
                    arrayVal[index++] = currenthead.next.val;
                    break;
                } else {
                    currenthead = currenthead.next;
                    if (currenthead.next == null) { //找到头了还没找到
                        arrayVal[index++] = 0;
                    }
                }
            }

            head=head.next;
         }
          int[] result= Arrays.copyOf(arrayVal,index);
          return result;
    }


    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
   }
}

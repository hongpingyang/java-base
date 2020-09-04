package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-07-14 21:00
 * description:
 * life for code
 */
public class 归并排序链表 {


    public static void main(String[] args) {


    }

    public ListNode sortList(ListNode head) {

        if (head == null || head.next == null)
            return head;

        //一个2倍的速度。 slow就是中间点
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode tmp = slow.next;
        slow.next = null; //断开

        ListNode left = sortList(head);
        ListNode right = sortList(tmp);

        //造一个假头
        ListNode h = new ListNode(0);
        ListNode res = h;

        while (left != null && right != null) {

            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        //最后还剩一个尾巴 不为null说明是剩下的
        h.next = left != null ? left : right;

        return res.next;
    }

   public class ListNode{
        int val;
        ListNode next;
        public ListNode(int val){
           this.val=val;
        }
   }

}

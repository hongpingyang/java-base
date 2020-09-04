package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-07-17 10:06
 * description:
 * life for code
 */
public class 反转链表 {






    public ListNode reverseList(ListNode head) {
        ListNode temp=head;
        while(temp.next!=null){

            ListNode removeNode=temp.next;
            temp.next=temp.next.next;

            //temp.next 移到前面去
            removeNode.next=head;
            head=removeNode;
        }
        return head;
    }

    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

}

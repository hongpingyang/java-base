package com.hong.py.algorithm.dtgh;

import java.util.Scanner;

/**
 * author: hongpy
 * create time: 2020-07-08 10:05
 * description:
 * life for code
 */
public class Test {
    private static ListNode header;
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        //while(scanner.hasNext()){
            int count=scanner.nextInt();
            int head=scanner.nextInt();
            Test m=new Test();
            m.doAction(head);
            //scanner.nextLine();
            for(int i=0;i<count-1;i++){
                //String line=scanner.nextLine();
                //String[] values=line.split(" ");
                int Node1 = scanner.nextInt();           //要插入的节点
                int Node2 = scanner.nextInt();           //插入在哪个节点之后
                m.append(Node1,Node2);
            }


            int dele=scanner.nextInt();
            m.delete(dele);
            StringBuffer result=new StringBuffer();
            for(ListNode start=header;start!=null;start=start.next){
                result.append(start.key+" ");
            }
            System.out.println(result);
        //}
    }

    public void doAction(int head){
        header=new ListNode();
        header.key=head;
    }

    public void append(int after,int before){
        ListNode node=new ListNode();
        node.key=after;
        for(ListNode head=header;head!=null;head=head.next){
            if(head.key==before){ //插入
                ListNode temp=head.next;
                head.next=node;
                node.next=temp;
            }
        }
    }

    public void delete(int node){
        if(header.key==node){
            header=header.next;
        }
        else{
            for(ListNode head=header;head.next!=null;head=head.next){
                if(head.next!=null&&head.next.key==node){ //删除
                    ListNode temp=head.next.next;
                    head.next=temp;
                }
            }
        }
    }


    public  class ListNode{
        int key;
        ListNode next;

        public ListNode() {}

    }
}

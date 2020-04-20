package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-04-19 13:21
 * description:
 * life for code
 *
 * 约瑟夫问题
 */
public class Josephus {

    private MyNode head;

    public MyNode getHead() {
        return head;
    }

    public void kill(Josephus josephus) {
        MyNode head=josephus.getHead();
        if(head.getNext()==null||head.getNext()==head) return;
        int position=0;
        do {
            position++;
            if (position == 3) { //杀掉喊的
                josephus.deleteNode(position);
                kill(josephus); //重新来
                break;
            }
        } while (true);
    }

    /**
     * 插入到尾部
     * @param val
     */
    public void InsertNode(int val) {
        MyNode myNode = new MyNode(val);
        if (head == null) {
            head=myNode;
        }
        else {
            if (head.getPre() == null) { //只有一个头节点
                head.setNext(myNode);
                myNode.setPre(head);
            }
            else {
                head.getPre().setNext(myNode);
                myNode.setPre(head.getPre());
            }
            myNode.setNext(head);
            head.setPre(myNode);
        }
    }

    public void deleteNode(int position) {
        if (position == 0) {
            head.getPre().setNext(head.getNext());
            head.getNext().setPre(head.getPre());
            head=head.getNext();

            System.out.println(head.getVal());
        }
        else {

            MyNode node=head;
            for (int i = 1; i < position; i++) {
                node=node.getNext();
            }

            node.getPre().setNext(node.getNext());
            node.getNext().setPre(node.getPre());
            head=node.getNext(); //杀掉的之后的当头

            node.setPre(null);
            node.setNext(null);


            System.out.println(node.getVal());
        }

    }

}


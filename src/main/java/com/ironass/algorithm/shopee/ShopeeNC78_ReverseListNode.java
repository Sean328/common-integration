package com.ironass.algorithm.shopee;


/**
 * 输入一个链表，反转链表后，输出新链表的表头。
 * 示例1
 * 输入
 * {1,2,3}
 * 返回值
 * {3,2,1}
 */
public class ShopeeNC78_ReverseListNode {


    public static void main(String[] args) {
        ListNode head  = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        head.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);

        ShopeeNC78_ReverseListNode reverseList = new ShopeeNC78_ReverseListNode();
        System.out.println(reverseList.reverseList(head));
    }

    public  ListNode reverseList(ListNode head) {
        if(head == null){
            return head;
        }

        ListNode pre = head;
        ListNode cur = head.next;
        ListNode tmp;

        while (cur != null){
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        head.next = null;
        return pre;
    }

}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public void setNext(ListNode node){
        this.next = node;
    }
}

package com.ironass.algorithm.leetcode;

public class ReverseListNode {

    private ListNode reverseNode(ListNode head){

        ListNode pre = null;
        ListNode next = null;

        while (head.next != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}

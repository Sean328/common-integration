package com.ironass.algorithm.leetcode;

/**
 * 给一个单向链表以及整数N，使得每N个元素为一组进行翻转。要求时间复杂度O(n), 空间复杂度O(1)。
 * <p>
 * <p>
 * 输入例子1:
 * {1,2,3,4,5,6,7,8},3
 * <p>
 * 输出例子1:
 * {3,2,1,6,5,4,8,7}
 */
public class Solution_25_ReverseKGroup {

    public static void main(String[] args) {

        ListNode head  = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        head.next.next = node3;
        head.next.next.next = node4;
        head.next.next.next.next = node5;

        ListNode res = reverseKGroup(head, 2);

        System.out.println(res);

    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode newListNode = new ListNode(0);
        ListNode prev = newListNode;
        ListNode curr = head;
        ListNode next;
        newListNode.next = head;
        int length = 0;

        while (head != null){
            length++;
            head = head.next;
        }

        head = newListNode.next;
        for(int i = 0; i < length/k; i++){
            for(int j=0; j< k-1;j++){
                next = curr.next;
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }

            prev = curr;
            curr = prev.next;
        }

        return newListNode.next;
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

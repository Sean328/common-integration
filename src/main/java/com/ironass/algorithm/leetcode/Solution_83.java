package com.ironass.algorithm.leetcode;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: 1->1->2
 * Output: 1->2
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 *
 * @author lixin
 * @date 2019-05-13 10:58
 **/
public class Solution_83 {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 题目大意： 给出一个有序的链表，去重输出
     * 解法:解法分很多，最好的方法是不新建链表而是直接在原有链表上做操作。
     * 注意题中给出的链表是单链表，只需要改当前节点的next节点即可
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {

        ListNode current = head;
        while (current !=null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            }else {
                current = current.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(1);
        listNode.next.next = new ListNode(2);
        listNode.next.next.next = new ListNode(3);
        listNode.next.next.next.next = new ListNode(3);

        Solution_83 solution_83 = new Solution_83();
        solution_83.deleteDuplicates(listNode);
    }
}

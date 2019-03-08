package com.ironass.leetcode;

/**
 * @author lixin
 * @date 2019-03-07 17:23
 **/

/**
 * @lc app=leetcode id=21 lang=java
 * <p>
 * [21] Merge Two Sorted Lists
 * <p>
 * https://leetcode.com/problems/merge-two-sorted-lists/description/
 * <p>
 * algorithms
 * Easy (45.74%)
 * Total Accepted:    517.8K
 * Total Submissions: 1.1M
 * Testcase Example:  '[1,2,4]\n[1,3,4]'
 * <p>
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */

public class Solution_21 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 使用递归解决
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }

        ListNode result = null;
        if(l1.val >= l2.val){
            result = l2;
            result.next = mergeTwoLists(l1,l2.next);
        }else {
            result = l1;
            result.next = mergeTwoLists(l1.next,l2);
        }

        return result;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(4);

        Solution_21 solution_21 = new Solution_21();
        ListNode result = solution_21.mergeTwoLists(l1,l2);
        System.out.println(result);
    }

}

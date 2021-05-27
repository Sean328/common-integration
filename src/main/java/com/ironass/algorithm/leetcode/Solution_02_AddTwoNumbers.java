package com.ironass.algorithm.leetcode;

/**
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 * *
 * 示例 1：
 * <p>
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 * <p>
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 * <p>
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * <p>
 * 提示：
 * <p>
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_02_AddTwoNumbers {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(6);
        head.next = node1;
        head.next.next = node2;
        node2.next = node3;

        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(4);
        head2.next.next = new ListNode(6);

        System.out.println(addTwoNumbers(head, head2));

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode res = new ListNode(-1);
        ListNode  begin = res;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int val;
            if(l1 == null && l2 ==null) {
                val = carry;
            } else if (l1 == null) {
                val = l2.val + carry;
                l2  = l2.next;
            } else if (l2 == null) {
                val = l1.val + carry;
                l1 = l1.next;
            } else {
                val = l1.val + l2.val + carry;
                l1 = l1.next;
                l2 = l2.next;
            }

            if (val > 9) {
                carry = val / 10;
                val = val % 10;
            } else {
                carry = 0;
            }
            begin.next = new ListNode(val);
            begin = begin.next;
        }

        return res.next;
    }

    /**
     * leetcode 评论最高答案
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers02(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int carry = 0;
        while(l1 != null || l2 != null || carry != 0) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            carry = sumVal / 10;

            ListNode sumNode = new ListNode(sumVal % 10);
            cursor.next = sumNode;
            cursor = sumNode;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        return root.next;
    }
}

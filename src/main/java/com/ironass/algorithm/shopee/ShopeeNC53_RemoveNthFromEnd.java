package com.ironass.algorithm.shopee;

/**
 * 给定一个链表，删除链表的倒数第 nn 个节点并返回链表的头指针
 * 例如，
 * 给出的链表为: 1\to 2\to 3\to 4\to 51→2→3→4→5, n= 2n=2.
 * 删除了链表的倒数第 nn 个节点之后,链表变为1\to 2\to 3\to 51→2→3→5.
 *
 * 备注：
 * 题目保证 nn 一定是有效的
 * 请给出请给出时间复杂度为\ O(n) O(n) 的算法
 * 示例1
 * 输入
 * {1,2},2
 * 返回值
 * {2}
 */
public class ShopeeNC53_RemoveNthFromEnd {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);

        removeNthFromEnd02(head, 1);
    }

    /**
     * 快慢指针的思路
     * @param head ListNode类
     * @param n int整型
     * @return ListNode类
     */
    public ListNode removeNthFromEnd (ListNode head, int n) {
        // write code here
        ListNode fast = head;
        ListNode slow = head;
        for(int i =0; i< n; i++){
            fast = fast.next;
        }
        //如果n的值等于链表的长度,直接返回去掉头结点的链表
        if(fast == null){
            return head.next;
        }

        while (fast.next != null){
            fast = fast.next;
            slow =slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    /**
     * 非快慢指针，先遍历长度得到要删除的位置，然后再遍历一次
     * @param head ListNode类
     * @param n int整型
     * @return ListNode类
     */
    public static ListNode removeNthFromEnd02 (ListNode head, int n) {
        // write code here
        if(head == null){
            return head;
        }

        int size = 0;
        ListNode node = head;
        while (node != null){
            size ++;
            node = node.next;
        }

        int position = size - n;
        if(position == 0){
            head = head.next;
            return head;
        }
        ListNode result = head;
        ListNode begin = result;
        for(int i=0; i< position-1; i++){
            result = result.next;
        }
        result.next = result.next.next;

        return begin;
    }
}

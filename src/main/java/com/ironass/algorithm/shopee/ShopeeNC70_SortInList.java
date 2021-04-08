package com.ironass.algorithm.shopee;


/**
 * 给定一个无序单链表，实现单链表的排序(按升序排序)。
 * 示例1
 * 输入
 * 复制
 * [1,3,2,4,5]
 * 返回值
 * 复制
 * {1,2,3,4,5}
 */
public class ShopeeNC70_SortInList {

    public static void main(String[] args) {
        ListNode head  = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        head.setNext(node3);
        node2.setNext(node4);
        node3.setNext(node2);

//        System.out.println(sortInList(head));
        System.out.println(sortInList2(head));
    }

    /**
     * 真正的选择排序
     * 1.建立哨兵节点
     * 2.每次从未排序的链表节点中找出最小的节点接到已排序链表的后面
     *
     * @param head ListNode类 the head node
     * @return ListNode类
     */
    public static ListNode sortInList (ListNode head) {
        // write code here
        if(head == null || head.next == null){
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode sortListNode = dummy;
        while (sortListNode.next != null) {
            ListNode pre = sortListNode;
            ListNode cur = sortListNode.next;
            ListNode preMin = null;
            ListNode min = null;
            while (cur != null){
                if(min == null || cur.val < min.val ){
                    preMin = pre;
                    min = cur;
                }
                pre = pre.next;
                cur = cur.next;
            }
            preMin.next = min.next;
            min.next = sortListNode.next;
            sortListNode.next = min;
            sortListNode = min;
        }

        return dummy.next;
    }

    /**
     * 虚假的选择排序：交换链表中的值
     * 会超时
     * @param head
     * @return
     */
    public static ListNode sortInList2 (ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode cur = head;
        while (head.next != null){
            ListNode next = head.next;
            while (next != null){
                if(next.val < cur.val){
                    int tmp = next.val;
                    next.val = cur.val;
                    cur.val = tmp;
                }
                next = next.next;
            }
            head = head.next;
        }

        return head;
    }
}

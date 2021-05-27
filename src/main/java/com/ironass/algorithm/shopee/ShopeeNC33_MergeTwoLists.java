package com.ironass.algorithm.shopee;

/**
 * 将两个有序的链表合并为一个新链表，要求新的链表是通过拼接两个链表的节点来生成的，且合并后新链表依然有序。
 */
public class ShopeeNC33_MergeTwoLists {

    public static void main(String[] args) {

    }

    /**
     *
     * @param l1 ListNode类
     * @param l2 ListNode类
     * @return ListNode类
     */
    public ListNode mergeTwoLists (ListNode l1, ListNode l2) {
        // write code here
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode currNode = dummy;
        while (l1 != null && l2 != null){
            if(l1.val > l2.val){
                currNode.next = l2;
                currNode = currNode.next;
                l2 = l2.next;
            } else {
                currNode.next = l1;
                currNode = currNode.next;
                l1 = l1.next;
            }
        }

        if(l1 != null){
            currNode.next =l1;
        }
        if(l2 != null){
            currNode.next = l2;
        }

        return dummy.next;
    }


    /**
     * 递归方式求解
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2 (ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }

        ListNode mergeNode;

        if(l1.val < l2.val){
            mergeNode = l1;
            mergeNode.next = mergeTwoLists2(l1.next,l2);
        }else {
            mergeNode = l2;
            mergeNode.next = mergeTwoLists2(l1, l2.next);
        }

        return mergeNode;
    }
}

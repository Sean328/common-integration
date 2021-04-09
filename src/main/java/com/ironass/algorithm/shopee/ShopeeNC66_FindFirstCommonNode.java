package com.ironass.algorithm.shopee;

import java.util.Stack;

/**
 * 输入两个链表，找出它们的第一个公共结点。
 * （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
 */
public class ShopeeNC66_FindFirstCommonNode {

    public static void main(String[] args) {

    }

    /**
     * 思路：
     *
     *   如果存在共同节点的话，那么从该节点，两个链表之后的元素都是相同的。
     *        也就是说两个链表从尾部往前到某个点，节点都是一样的。
     *        我们可以用两个栈分别来装这两条链表。一个一个比较出来的值。
     *        找到第一个相同的节点。
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null || pHead2 == null){
            return null;
        }
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        while (pHead1 != null){
            stack1.push(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null){
            stack2.push(pHead2);
            pHead2 = pHead2.next;
        }
        ListNode res = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()  && stack1.peek() == stack2.peek()){
            stack2.pop();
            res = stack1.pop();
        }
        return res;
    }

}

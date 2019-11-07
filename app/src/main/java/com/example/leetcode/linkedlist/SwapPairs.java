package com.example.leetcode.linkedlist;

/**
 * 24. 两两交换链表中的节点
 */
public class SwapPairs {
    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     *  
     *
     * 示例:
     *
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：这种方法单独处理了头节点，其实可以添加一个空的头结点，使得代码更简洁
     *
     * 加空头的方法就略过啦~~~
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode p = head;
        head = p.next;

        //后置节点
        ListNode tmp = p.next.next;
        //前置节点初始化
        ListNode pre = p;
        //反转前两个节点
        p.next.next = p;
        p.next = tmp;
        p = tmp;


        while (p != null && p.next != null) {
            //更新前置节点和后置节点
            tmp = p.next.next;
            pre.next = p.next;
            pre = p;

            //反转两个节点
            p.next.next = p;
            p.next = tmp;
            p = tmp;
        }
        return head;
    }
}

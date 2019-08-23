package com.example.leetcode.linkedlist;

/**
 * 环形链表
 */
public class HasCycle {
    /**
     * 给定一个链表，判断链表中是否有环。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/linked-list-cycle
     *
     * 方法一：hash表 空间复杂度O（n）
     * 方法二：快慢指针 空间复杂度O（1）
     * 如果列表中不存在环，最终快指针将会最先到达尾部，此时我们可以返回 false。
     *
     * 现在考虑一个环形链表，把慢指针和快指针想象成两个在环形赛道上跑步的运动员（分别称之为慢跑者与快跑者）。
     * 而快跑者最终一定会追上慢跑者。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/linked-list-cycle/solution/huan-xing-lian-biao-by-leetcode/
     * 来源：力扣（LeetCode）
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if(fast== null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;

        }
        return true;
    }
}

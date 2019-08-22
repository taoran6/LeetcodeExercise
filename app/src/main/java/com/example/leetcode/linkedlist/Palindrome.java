package com.example.leetcode.linkedlist;

/**
 * 回文链表
 */
public class Palindrome {
    /**
     * 请判断一个链表是否为回文链表。
     *
     * 示例 1:
     *
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     *
     * 输入: 1->2->2->1
     * 输出: true
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     *
     * 快慢指针,反转一半的链表
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        ListNode prv = null;
        ListNode behind = null;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            behind = slow.next;
            slow.next = prv;
            prv = slow;
            slow = behind;
        }

        if(fast.next != null) {
            fast = slow.next;
            slow.next = prv;
        } else {
            fast = slow.next;
            slow = prv;

        }

        while (fast != null) {
            if (fast.val != slow.val) return false;
            fast = fast.next;
            slow = slow.next;
        }

        return true;
    }
}

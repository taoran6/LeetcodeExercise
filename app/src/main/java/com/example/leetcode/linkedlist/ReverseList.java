package com.example.leetcode.linkedlist;

/**
 * 反转链表
 */
public class ReverseList {
    /**
     * 反转一个单链表。
     * <p>
     * 示例:
     * <p>
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * 进阶:
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 方法一：迭代
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode mid = head;
        ListNode before = null;

        while (mid != null) {
            ListNode behind = mid.next;
            mid.next = before;
            before = mid;
            mid = behind;
        }

        return before;
    }

    /**
     * 方法二：递归
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        else {
            ListNode k = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return k;
        }
    }

}

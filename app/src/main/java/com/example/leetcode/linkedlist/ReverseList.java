package com.example.leetcode.linkedlist;

/**
 * 反转链表
 */
public class ReverseList {
    /**
     * 反转一个单链表。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * 进阶:
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
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

    /**
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     *
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        //添加一个空的头结点使得操作一致
        ListNode emptyHead = new ListNode(0);
        emptyHead.next = head;

        ListNode pre = emptyHead;

        for(int i = 1; i < m; i++) {
            pre = pre.next;
        }
        //tail保存的第m-1个节点
        ListNode tail = pre.next;
        ListNode p = tail;
        ListNode nodeNext = tail.next;

        //翻转 m 到 n 的节点
        for (int i = 0; i < n - m; i++) {
            ListNode nextTmp = nodeNext.next;
            nodeNext.next = p;
            p = nodeNext;
            nodeNext = nextTmp;
        }

        //此时nextNode保存的第n + 1个节点
        tail.next = nodeNext;
        pre.next = p;

        return emptyHead.next;
    }

}

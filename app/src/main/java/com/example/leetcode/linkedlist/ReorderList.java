package com.example.leetcode.linkedlist;

/**
 * 143. 重排链表
 */
public class ReorderList {
    /**
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * 示例 1:
     *
     * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
     * 示例 2:
     *
     * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reorder-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param head
     *
     * 方法一：快慢指针 + 反转链表 + 拼接
     */
    public void reorderList(ListNode head) {
        if(head == null) return;

        //快慢指针寻找中间节点
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //翻转后半部分链表
        ListNode pre = slow;
        slow = slow.next;

        while (slow != null) {
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }

        //两个链表重排
        ListNode left = head;
        ListNode right = pre;
        while (left!= right && left.next != right) {
            ListNode next = right.next;
            right.next = left.next;
            left.next = right;

            left = right.next;
            right = next;
        }

        //节点个数为奇数，如 1-> 2 <- 3，此时left和right均指向2
        if(left == right) left.next = null;
        //节点个数为偶数，如 1-> 2 -> 3 <- 4，此时left指向2，right指向3
        else {
            //left.next = right;
            right.next = null;
        }
    }

    /**
     * 方法二：递归
     *
     * 如果我们的递归函数能够返回当前头元素对应的尾元素，并且将头元素和尾元素之间的链表按要求完成，那就变得简单了。
     * https://leetcode-cn.com/problems/reorder-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-34/
     */
    public void reorderList2(ListNode head) {

        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        int len = 0;
        ListNode h = head;
        //求出节点数
        while (h != null) {
            len++;
            h = h.next;
        }

        reorderListHelper(head, len);
    }

    /**
     * 按要求处理好链表后返回链表的尾节点
     * @param head
     * @param len
     * @return
     */
    private ListNode reorderListHelper(ListNode head, int len) {
        //递归出口
        if (len == 1) {
            ListNode outTail = head.next;
            head.next = null;
            return outTail;
        }
        if (len == 2) {
            ListNode outTail = head.next.next;
            head.next.next = null;
            return outTail;
        }
        //得到对应的尾节点，并且将头结点和尾节点之间的链表通过递归处理
        ListNode tail = reorderListHelper(head.next, len - 2);
        ListNode subHead = head.next;//中间链表的头结点
        head.next = tail;
        ListNode outTail = tail.next;  //上一层 head 对应的 tail
        tail.next = subHead;
        return outTail;
    }

}

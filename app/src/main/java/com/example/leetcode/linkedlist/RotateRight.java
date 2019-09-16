package com.example.leetcode.linkedlist;

/**
 * 旋转链表
 */
public class RotateRight {
    /**
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     *
     * 示例 1:
     *
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     * 示例 2:
     *
     * 输入: 0->1->2->NULL, k = 4
     * 输出: 2->0->1->NULL
     * 解释:
     * 向右旋转 1 步: 2->0->1->NULL
     * 向右旋转 2 步: 1->2->0->NULL
     * 向右旋转 3 步: 0->1->2->NULL
     * 向右旋转 4 步: 2->0->1->NULL
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/rotate-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return null;

        int x = 0;
        ListNode p = head;

        while (p.next != null) {
            x ++;
            p = p.next;
        }
        x++;        //此时x即为链表长度
        p.next = head;
        k = (x - k % x) % x;        //求两次模，避免 k = x 的情况下多余的移动
        for (int i = 0; i < k; i++) p = p.next;
        head = p.next;
        p.next = null;
        return head;
    }
}

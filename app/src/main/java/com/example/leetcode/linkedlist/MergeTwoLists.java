package com.example.leetcode.linkedlist;

/**
 * 合并两个有序链表
 */
public class MergeTwoLists {

    /**
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     *
     * 示例：
     *
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        else if (l2 == null) return l1;
        else if (l1 == null) return l2;


        ListNode l3;
        ListNode p;
        if (l1.val <= l2.val) {
            l3 = new ListNode(l1.val);
            l1 = l1.next;
        } else {
            l3 = new ListNode(l2.val);
            l2 = l2.next;
        }
        p = l3;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                p.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                p.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) {
            p.next = l1;
        } else {
            p.next = l2;
        }
        return l3;
    }
}


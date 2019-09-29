package com.example.leetcode.linkedlist;

/**
 * 排序链表
 */
public class SortList {
    /**
     * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
     *
     * 示例 1:
     *
     * 输入: 4->2->1->3
     * 输出: 1->2->3->4
     * 示例 2:
     *
     * 输入: -1->5->3->4->0
     * 输出: -1->0->3->4->5
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sort-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一： 使用快速排序
     *
     * 由于递归，带来O(logn)的空间复杂度
     */

    public ListNode sortList(ListNode head) {
        if(head == null) return null;

        return quickSortList(head, null);
    }

    //快速排序，返回头结点
    private ListNode quickSortList(ListNode head, ListNode tail) {
        if(head == null || head == tail) return head;
        ListNode pivot = head;    //锚点
        ListNode p = head.next;
        ListNode pre = head;

        while (p != tail) {
            if(p.val < pivot.val) {
                //小于pivot.val的全部插到链表头部
                pre.next = p.next;
                p.next = head;
                head = p;
                p = pre.next;
            } else {
                p = p.next;
                pre = pre.next;
            }
        }

        //递归排序后半部分
        pivot.next = quickSortList(pivot.next, tail);
        //递归排序左半部分
        return quickSortList(head, pivot);
    }

    /**
     * 方法二：归并排序
     *
     * 递归法，仍带来O(logn)的空间复杂度
     */
    public ListNode sortList2(ListNode head) {
        //todo 看得我有点想吐，过一会儿再看吧
        return head;
    }

}

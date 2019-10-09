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
        if(head == null || head.next == null) return head;

        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList2(head);
        ListNode right = sortList2(tmp);
        ListNode h = new ListNode(0);
        ListNode ans = h;

        while (left != null && right != null) {
            if(left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left == null ? right : left;
        return ans.next;
    }

    /**
     * TODO 方法三：自底向上，非递归法
     */
    class Solution {
        public ListNode sortList(ListNode head) {
            ListNode dummyHead = new ListNode(0);
            dummyHead.next = head;
            ListNode p = head;
            int length = 0;
            while (p != null) {
                ++length;
                p = p . next;
            }

            for (int size = 1; size < length; size <<= 1) {
                ListNode cur = dummyHead.next;
                ListNode tail = dummyHead;

                while (cur!= null) {
                    ListNode left = cur;
                    ListNode right = cut(left, size); // left . @ . @ right . @ . @ . @...
                    cur = cut(right, size); // left . @ . @ right . @ . @  cur . @ . ...

                    tail . next = merge(left, right);
                    while (tail.next != null) {
                        tail = tail . next;
                    }
                }
            }
            return dummyHead.next;
        }

        private ListNode cut(ListNode head, int n) {
            ListNode p = head;
            while (--n != 0 && p != null) {
                p = p . next;
            }

            if (p == null) return null;

            ListNode next = p  .  next;
            p . next = null;
            return next;
        }

        ListNode merge(ListNode l1, ListNode l2) {
            ListNode dummyHead = new ListNode(0);
            ListNode p = dummyHead;
            while (l1!= null && l2 !=null) {
                if (l1 . val < l2 . val) {
                    p . next = l1;
                    p = l1;
                    l1 = l1 . next;
                } else {
                    p . next = l2;
                    p = l2;
                    l2 = l2 . next;
                }
            }
            p . next = l1 != null ? l1 : l2;
            return dummyHead.next;
        }
    }

}

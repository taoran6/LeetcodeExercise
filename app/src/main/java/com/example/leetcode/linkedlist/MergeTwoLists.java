package com.example.leetcode.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

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

    /**
     * 23. 合并K个排序链表
     *
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     *
     * 示例:
     *
     * 输入:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：使用优先级队列
     * 时间复杂度O(n*log(k)), 空间复杂度O(k), 因为堆调整复杂度是log(k)
     * 其中n是所有节点数之和，k是链表个数
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;

        ListNode emptyNode = new ListNode(0);
        ListNode p = emptyNode;

        //使用堆，自定义排序规则
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode head : lists) {
            //记得判空
            if(head != null) {
                priorityQueue.offer(head);
            }
        }
        while (!priorityQueue.isEmpty()) {
            ListNode node = priorityQueue.poll();
            p.next = node;
            p = p.next;
            if(node.next != null) {
                priorityQueue.offer(node.next);
            }
        }
        return emptyNode.next;
    }

    /**
     * 方法二：分治，链表两两合并,适合多线程
     *
     * 时间复杂度O(n logk)，空间复杂度O(1)
     * 借助了合并两个链表
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        int length = lists.length;
        while (length != 1) {
            for(int i = 0; i < length / 2; i++) {
                //合并两个链表，注意到这里将位置2i与2i+1的两个链表合并到位置i上。
                lists[i] = mergeTwoLists(lists[2*i], lists[2*i + 1]);
            }
            //处理奇数的情况。把最后一个链表放到下次待求解数组的末端，顺便解决向上取整的问题
            if((length & 1) != 0) {
                lists[length / 2] = lists[length - 1];
                length ++;
            }
            length = length / 2;
        }
        return lists[0];
    }


}


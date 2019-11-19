package com.example.leetcode.linkedlist;

/**
 * 建立链表，测试用
 */
public class BuildLinkedList {
    public static ListNode buildList(int[] vals) {
        if(vals.length == 0) return null;

        ListNode head = new ListNode(vals[0]);
        ListNode prev = head;
        for(int i = 1; i < vals.length; i++) {
            ListNode node = new ListNode(vals[i]);
            prev.next = node;
            prev = node;
        }
        return head;
    }
}

package com.example.leetcode;

import com.example.leetcode.array.LastStoneWeight;
import com.example.leetcode.linkedlist.HasCycle;
import com.example.leetcode.linkedlist.ListNode;

/**
 * 测试用
 */
public class MainTest {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        System.out.println(new HasCycle().detectCycle(node1));
    }
}

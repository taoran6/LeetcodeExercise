package com.example.leetcode.math;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 孩子们的游戏(圆圈中最后剩下的数)孩子们的游戏(圆圈中最后剩下的数)
 */
public class LastRemaining {
    /**
     * 题目描述
     * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然
     * 也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编
     * 号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不
     * 再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用
     * 表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。请你试着想下,哪个小朋友会得到这份礼品
     * 呢？(注：小朋友的编号是从0到n-1)
     *
     * 如果没有小朋友，请返回-1
     *
     * 方法一：模拟环
     * 也可以直接遍历Array做-1的标记，思路都是差不多的
     */
    public int LastRemaining_Solution1(int n, int m) {
        if(n <= 0 || m <= 0) return -1;
        if(m == 1) return n-1;

        ListNode head = new ListNode(0);
        ListNode tail = head;
        for (int i = 1; i < n; i++) {
            tail.next = new ListNode(i);
            tail = tail.next;
        }
        tail.next = head;
        int size = n;

        ListNode p = head;
        while (p.next != p) {
            for (int i = 0; i < ((m-2) % size); i++) p = p.next;
            p.next = p.next.next;
            p = p.next;
            size --;
        }

        return p.val;
    }

    public class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    /**
     * 方法二：数学归纳法
     * 原因可以看剑指offer，归纳起来有点复杂
     * 链接：https://www.nowcoder.com/questionTerminal/f78a359491e64a50bce2d89cff857eb6?f=discussion
     *         来源：牛客网
     */
    public int LastRemaining_Solution(int n, int m) {
        if(n==0)
            return -1;
        if(n==1)
            return 0;
        else
            return (LastRemaining_Solution(n-1,m)+m)%n;

    }
}

package com.example.leetcode.linkedlist;

/**
 * 反转链表
 */
public class ReverseList {
    /**
     * 206. 反转链表
     *
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
     * 92. 反转链表 II
     *
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

    /**
     * 方法二：递归翻转链表的一部分
     *
     * https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/bu-bu-chai-jie-ru-he-di-gui-di-fan-zhuan-lian-biao/
     */
    ListNode successor = null; // 后驱节点

    // 反转以 head 为起点的 n 个节点，返回新的头结点
    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }

    ListNode reverseBetween2(ListNode head, int m, int n) {
        // base case
        if (m == 1) {
            return reverseN(head, n);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween2(head.next, m - 1, n - 1);
        return head;
    }


    /**
     * 25. K 个一组翻转链表
     *
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     *
     * k 是一个正整数，它的值小于或等于链表的长度。
     *
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     * 示例 :
     *
     * 给定这个链表：1->2->3->4->5
     *
     * 当 k = 2 时，应当返回: 2->1->4->3->5
     *
     * 当 k = 3 时，应当返回: 3->2->1->4->5
     *
     * 说明 :
     *
     * 你的算法只能使用常数的额外空间。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup0(ListNode head, int k) {
        //用一个空的头结点方便操作
        ListNode emptyHead = new ListNode(0);
        emptyHead.next = head;


        ListNode prev = emptyHead;
        ListNode node = head;
        //第K个节点的位置
        ListNode nodeK = head;

        while (nodeK != null) {
            //先向前走k个节点，如果不够K个节点说明不用再反转了，直接返回
            for (int i = 0; i < k; i++) {
                if(nodeK == null) return emptyHead.next;
                nodeK = nodeK.next;
            }

            //反转这k个节点的链表
            ListNode preK = prev;
            while (node != nodeK) {
                ListNode next = node.next;
                node.next = prev;
                prev = node;
                node = next;
            }

            //反转后与前面和后面的链表连接起来
            ListNode kTail = preK.next;
            kTail.next = nodeK;
            preK.next = prev;
            prev = kTail;
        }

        return emptyHead.next;
    }

    /**
     * 方法二：递归
     *
     * 天呐，用时竟然 0ms
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k == 1) return head;

        //先找第K个节点，不够k个直接返回
        ListNode nodeK = head;
        for(int i = 0; i < k; i++) {
            if (nodeK == null) return head;
            nodeK = nodeK.next;
        }

        //反转这k个节点
        ListNode prev = head;
        ListNode node = head.next;
        while (node != nodeK) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        //剩余的递归
        head.next = reverseKGroup(nodeK, k);
        return prev;
    }
}

package com.example.leetcode.linkedlist;

/**
 * 环形链表
 */
public class HasCycle {
    /**
     * 给定一个链表，判断链表中是否有环。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/linked-list-cycle
     *
     * 方法一：hash表 空间复杂度O（n）
     * 方法二：快慢指针 空间复杂度O（1）
     * 如果列表中不存在环，最终快指针将会最先到达尾部，此时我们可以返回 false。
     *
     * 现在考虑一个环形链表，把慢指针和快指针想象成两个在环形赛道上跑步的运动员（分别称之为慢跑者与快跑者）。
     * 而快跑者最终一定会追上慢跑者。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/linked-list-cycle/solution/huan-xing-lian-biao-by-leetcode/
     * 来源：力扣（LeetCode）
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if(fast== null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;

        }
        return true;
    }

    /**
     * 环形链表 II
     *
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     *
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos
     * 是 -1，则在该链表中没有环。
     *
     * 说明：不允许修改给定的链表。
     *
     *  
     * 示例 1：
     *
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：tail connects to node index 1
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     *
     *
     * 示例 2：
     *
     * 输入：head = [1,2], pos = 0
     * 输出：tail connects to node index 0
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     *
     *
     * 示例 3：
     *
     * 输入：head = [1], pos = -1
     * 输出：no cycle
     * 解释：链表中没有环。
     *
     *
     *  
     *
     * 进阶：
     * 你是否可以不用额外空间解决此题？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：这个方法是我自己想的哈，执行用时149ms
     */
    public ListNode mDetectCycle(ListNode head) {
        if(head == null || head.next == null) return null;

        ListNode slow = head;
        ListNode fast = head.next;

        int cycle = 1;      //cycle表示环的长度
        while (slow != fast) {
            if(fast.next == null || fast.next.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
            cycle ++;
        }

        fast = head;
        for(int i = 0; i < cycle; i++) {
            fast = fast.next;
        }
        //入口在头结点
        if(fast == head) return head;


        slow = head;
        fast = head;
        do {
            slow = slow.next;       //慢指针走一步
            for(int i = 0; i <= cycle; i++) {       //快指针走（1 + cycle）步
                fast = fast.next;
            }
        } while (slow != fast);
        //相遇时停留的点为入口
        return slow;
    }


    /**
     * 方法二：Floyd 算法
     *
     * 第1阶段，找出列表中是否有环，如果没有环，可以直接返回 null 并退出。否则，用相遇节点 来找到环的入口。
     *
     * 给定阶段 1 找到的相遇点，阶段 2 将找到环的入口。首先我们初始化额外的两个指针： ptr1 ，指向链表的头，
     * ptr2 指向相遇点。然后，我们每次将它们往前移动一步，直到它们相遇，它们相遇的点就是环的入口，返回这个节点。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/huan-xing-lian-biao-ii-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;

        // ！注意这里初始条件和上一个方法稍有不同，起点为head
        ListNode slow = head.next;
        ListNode fast = head.next.next;

        //阶段一：证明是否存在环
        while (slow != fast) {
            if(fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
        }

        //阶段二：已存在环，找到入口
        slow = head;    //slow初始为head，fast初始为之前相遇的那个点，原因嘛可以看题解的证明
        while (slow != fast) {
            //两指针都向前一步
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

}

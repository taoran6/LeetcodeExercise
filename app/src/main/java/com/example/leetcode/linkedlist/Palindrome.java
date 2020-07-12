package com.example.leetcode.linkedlist;

/**
 * 回文
 */
public class Palindrome {
    /**
     * 234. 回文链表
     *
     * 请判断一个链表是否为回文链表。
     *
     * 示例 1:
     *
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     *
     * 输入: 1->2->2->1
     * 输出: true
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     *
     * 快慢指针,反转一半的链表
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        ListNode prv = null;
        ListNode behind = null;
        // 快慢指针
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            behind = slow.next;
            slow.next = prv;
            prv = slow;
            slow = behind;
        }

        // 反转链表的一半
        if(fast.next != null) {
            fast = slow.next;
            slow.next = prv;
        } else {
            fast = slow.next;
            slow = prv;

        }

        // 回文对比
        while (fast != null) {
            if (fast.val != slow.val) return false;
            fast = fast.next;
            slow = slow.next;
        }

        return true;
    }

    /**
     * 方法二:使用递归的性质
     *
     * 不过这种方法left和right都是将整个链表都遍历了一遍，其实只用遍历一半就好
     */
    // 左侧指针
    ListNode left;
    public boolean isPalindrome2(ListNode head) {
        left = head;
        return reverse(head);
    }

    private boolean reverse(ListNode right) {
        if(right == null) return true;

        boolean ans = reverse(right.next);
        // 后序遍历代码
        ans = ans && (left.val == right.val);
        left = left.next;

        return ans;
    }

    /**
     * 876. 链表的中间结点
     *
     * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
     *
     * 如果有两个中间结点，则返回第二个中间结点。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：[1,2,3,4,5]
     * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
     * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
     * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
     * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
     * 示例 2：
     *
     * 输入：[1,2,3,4,5,6]
     * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
     * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
     *  
     *
     * 提示：
     *
     * 给定链表的结点数介于 1 和 100 之间。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/middle-of-the-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

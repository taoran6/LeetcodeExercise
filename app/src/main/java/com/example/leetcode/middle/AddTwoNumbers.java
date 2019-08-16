package com.example.leetcode.middle;

/**
 * 两数相加
 */
public class AddTwoNumbers {
    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
     * 并且它们的每个节点只能存储 一位 数字。
     *
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     *
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 示例：
     *
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int flag;
        int x = l1.val + l2.val;
        ListNode l = new ListNode(x % 10);
        ListNode p = l;
        flag = x / 10;
        l1 = l1.next;
        l2 = l2.next;
        while (l1 != null && l2 != null) {
            int tmp = l1.val + l2.val + flag;
            ListNode node = new ListNode(tmp % 10);
            flag = tmp / 10;
            p.next = node;
            p = p.next;
            l1 = l1.next;
            l2 = l2.next;
        }


        ListNode l3 = null;
        if (l1 != null && l2 == null) {     //剩余l1
            l3 = l1;
        } else if (l1 == null && l2 != null) {      //剩余l2
            l3 = l2;
        }
        p.next = l3;

        while (l3 != null && flag > 0) {
            int tmp = l3.val + flag;
            l3.val = tmp % 10;
            flag = tmp / 10;
            p.next = l3;
            p = p.next;
            l3 = l3.next;
        }

        if (l3 == null) {        //最后一位是否需要添加
            if (flag > 0) {
                ListNode node = new ListNode(flag);
                p.next = node;
            }
        }


        return l;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

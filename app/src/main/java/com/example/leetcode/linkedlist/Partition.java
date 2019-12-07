package com.example.leetcode.linkedlist;

/**
 * 86. 分隔链表
 */
public class Partition {
    /**
     * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
     *
     * 你应当保留两个分区中每个节点的初始相对位置。
     *
     * 示例:
     *
     * 输入: head = 1->4->3->2->5->2, x = 3
     * 输出: 1->2->2->4->3->5
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/partition-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode partition(ListNode head, int x) {
        //存储小于x的链表
        ListNode headSmall = new ListNode(0);
        //存储大于等于x的链表
        ListNode headBig = new ListNode(0);
        ListNode smallP = headSmall;
        ListNode bigP = headBig;

        while (head != null) {
            ListNode next = head.next;
            //这里不要忘了next置为空，否则顺序就乱了，内存溢出
            head.next = null;

            if(head.val < x) {
                smallP.next = head;
                smallP = smallP.next;
            } else {
                bigP.next = head;
                bigP = bigP.next;
            }
            head = next;
        }

        //两个链表一拼，OK了
        smallP.next = headBig.next;
        return headSmall.next;
    }
}

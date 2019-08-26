package com.example.leetcode.linkedlist;

/**
 * 相交链表
 */
public class IntersectionNode {
    /**
     * 编写一个程序，找到两个单链表相交的起始节点。
     * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
     *
     *
     * 方法一：hash表存储节点地址 代码略
     *
     *
     * 方法二：双指针
     *    创建两个指针 pApA 和 pBpB，分别初始化为链表 A 和 B 的头结点。然后让它们向后逐结点遍历。
     *    当 pApA 到达链表的尾部时，将它重定位到链表 B 的头结点 (你没看错，就是链表 B); 类似的，当 pBpB 到达
     * 链表的尾部时，将它重定位到链表 A 的头结点。
     *    若在某一时刻 pApA 和 pBpB 相遇，则 pApA/pBpB 为相交结点。
     * 想弄清楚为什么这样可行, 可以考虑以下两个链表: A={1,3,5,7,9,11} 和 B={2,4,9,11}，相交于结点 9。
     * 由于 B.length (=4) < A.length (=6)，pBpB 比 pApA 少经过 22 个结点，会先到达尾部。将 pBpB 重定向到
     * A 的头结点，pApA 重定向到 B 的头结点后，pBpB 要比 pApA 多走 2 个结点。因此，它们会同时到达交点。
     *    如果两个链表存在相交，它们末尾的结点必然相同。因此当 pApA/pBpB 到达链表结尾时，记录下链表 A/B 对应
     * 的元素。若最后元素不相同，则两个链表不相交。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/xiang-jiao-lian-biao-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}

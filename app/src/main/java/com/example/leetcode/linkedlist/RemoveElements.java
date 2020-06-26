package com.example.leetcode.linkedlist;

/**
 * 移除链表元素
 */
public class RemoveElements {
    /**
     * 删除链表中等于给定值 val 的所有节点。
     *
     * 示例:
     *
     * 输入: 1->2->6->3->4->5->6, val = 6
     * 输出: 1->2->3->4->5
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {

        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val == val) {
                if(pre != null) pre.next = curr.next;
                else head = curr.next;
            } else {
                pre = curr;
            }
            curr = curr.next;
        }

        return head;
    }

    /**
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
     *
     * 示例 1:
     *
     * 输入: head = [4,5,1,9], node = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * 示例 2:
     *
     * 输入: head = [4,5,1,9], node = 1
     * 输出: [4,5,9]
     * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     *  
     *
     * 说明:
     *
     * 链表至少包含两个节点。
     * 链表中所有节点的值都是唯一的。
     * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
     * 不要从你的函数中返回任何结果。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 因为，我们无法访问我们想要删除的节点 之前 的节点，我们始终不能修改该节点的 next 指针。相反，我们必须将想
     * 要删除的节点的值替换为它后面节点中的值，然后删除它之后的节点。
     * (emmmm......题目有点没说明白啊，真的秀，题目不难，主要是理解问题。。。)
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        ListNode next = node.next;
        node.val = next.val;
        node.next = next.next;
    }

    /**
     * 83. 删除排序链表中的重复元素
     *
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     *
     * 示例 1:
     *
     * 输入: 1->1->2
     * 输出: 1->2
     * 示例 2:
     *
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        ListNode p = head;
        while (p.next != null) {
            if(p.val == p.next.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }

    /**
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
     * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
     */
    public ListNode deleteDuplication(ListNode pHead)
    {
        ListNode head = new ListNode(0);
        head.next = pHead;

        ListNode pre = head;
        ListNode p = pHead;
        while(p != null) {
            ListNode tail = p;
            while (tail.next != null && tail.next.val == p.val) {
                tail = tail.next;
            }
            if(tail == p) {
                p = p.next;
                pre = pre.next;
            } else {
                p = tail.next;
                pre.next = p;
            }

        }
        return head.next;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null) return null;

        //快慢指针
        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null) {
            if(fast.val != slow.val) {
                slow.next = fast;
                // slow++;
                slow = slow.next;
            }
            // fast++
            fast = fast.next;
        }

        //注意， 断开与后面重复元素的连接
        slow.next = null;

        return head;
    }

    /**
     * 删除链表的倒数第N个节点
     *
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     *
     * 示例：
     *
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     *
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：
     *
     * 给定的 n 保证是有效的。
     *
     * 进阶：
     *
     * 你能尝试使用一趟扫描实现吗？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(n < 1 || head == null) return head;
        ListNode pre = null;
        ListNode nNode;
        ListNode p = head;
        for(int i = 0; i < n; i++) {
            p = p.next;
        }
        nNode = head;
        while (p != null) {
            pre = nNode;
            nNode = nNode.next;
            p = p.next;
        }
        if(pre == null) return head.next;
        pre.next = nNode.next;
        return head;
    }

    /**
     * 面试题 02.01. 移除重复节点
     *
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
     *
     * 示例1:
     *
     *  输入：[1, 2, 3, 3, 2, 1]
     *  输出：[1, 2, 3]
     * 示例2:
     *
     *  输入：[1, 1, 1, 1, 2]
     *  输出：[1, 2]
     * 提示：
     *
     * 链表长度在[0, 20000]范围内。
     * 链表元素在[0, 20000]范围内。
     * 进阶：
     *
     * 如果不得使用临时缓冲区，该怎么解决？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicate-node-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：使用HashMap，略
     * 方法二：时间换空间，时间复杂度O(n^2)。
     *      本来想说用快排先排序的，但是快排和堆排都是不稳定的排序，而归并排序又有O(n)空间复杂度
     *      就算有了稳定排序，原本的数字的顺序也被打乱了，输出肯定是有序的，不符合题意，所以只能O(n^2)
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        if(head == null) return head;

        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null) {
            if(! isDuplicate(head, slow, fast.val)) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }

        slow.next = null;
        return head;
    }

    public boolean isDuplicate(ListNode head, ListNode end, int target) {
        ListNode p = head;
        while(p != end) {
            if(p.val == target) return true;
            p = p.next;
        }
        return p.val == target;
    }
}

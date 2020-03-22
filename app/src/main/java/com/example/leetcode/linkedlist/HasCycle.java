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
     *
     * 图解可以看 https://blog.csdn.net/coraline_m/article/details/102594511  公式很详细
     */
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;

        // ！注意这里提前走了一步，否则slow和fast就相等了
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

    /**
     * 287. 寻找重复数
     *
     * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整
     * 数。假设只有一个重复的整数，找出这个重复的数。
     *
     * 示例 1:
     *
     * 输入: [1,3,4,2,2]
     * 输出: 2
     * 示例 2:
     *
     * 输入: [3,1,3,4,2]
     * 输出: 3
     * 说明：
     *
     * 不能更改原数组（假设数组是只读的）。
     * 只能使用额外的 O(1) 的空间。
     * 时间复杂度小于 O(n2) 。
     * 数组中只有一个重复的数字，但它可能不止重复出现一次。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 这道题（据说）花费了计算机科学界的传奇人物Don Knuth 24小时才解出来。并且我只见过一个人
     * （注：Keith Amling）用更短时间解出此题。
     *
     * 方法：
     * 快慢指针，思想和环形链表思想一样，所以放在了这里
     * 本题可以使用数组配合下标，抽象成链表问题，nums = [2, 5, 9 ,6, 9, 3, 8, 9, 7, 1]，
     * 构造成链表就是：2->[9]->1->5->3->6->8->7->[9]，也就是在[9]处循环。
     *
     * 对于有不止重复出现一次的情况，多的那个重复的节点其实不会被遍历到，实际上还是一条环形链表，只不过链表的长度
     * 比 n 要短很多。可以自己写个例子模拟一遍就明白了。
     */
    public int findDuplicate(int[] nums) {
        //head = nums[0]
        int slow = nums[nums[0]];   //slow = head.next
        int fast = nums[slow];   //fast = head.next.next

        while (fast != slow) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        slow = nums[0];
        while (fast != slow) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }
    /**
     * 写在后面的话：一般而言，面试官会更注重基础、常见的知识的考察。我个人觉得按照 ：1、哈希表判重，2、再说排序
     * 以后相邻元素相等即找到的重复；3、桶排序；4、二分法。然后分析一下这些方法的时间复杂度、空间复杂度，做一个简
     * 单比较，我觉得应该就很不错了。对于这道题，快慢指针的方法太tricky了，感觉面试的时候说方法三是给自己挖坑。
     */

    /**
     * 本题的关键是用利用n+1个数，每个数都在1到n之间。
     *
     * 重复的数也是在1到n之间。
     *
     * 对[1,n]二分，确定重复的数在区间的那边。
     *
     * 例如：[4,3,1,2,1]
     *
     * n=4
     *
     * mid=(1+4)/2=2
     *
     * 小于等于mid=2的数的个数为3,[1,2,1]。
     *
     * 明确如果没有重复，<=2的数的个数应该为2.
     *
     * 这说明重复的数在[1,2]这个区间中。
     *
     * mid=(1+2)/2=1。
     *
     * <=mid=1的数的个数为2.
     *
     * 如果没有重复，<=1的数的个数应该为1.
     *
     * 说明1重复了。
     *
     * 作者：jason-2
     * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number/solution/er-fen-fa-by-jason-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        int start = 1;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            int leftNum = 0;
            for (int i : nums){
                if(i <= mid) leftNum ++;
            }
            if(leftNum > mid) end = mid;
            else start = mid+1;
        }
        return start;
    }

    /**
     * 输入两个链表，找出它们的第一个公共结点。
     *
     * 有公共节点的链表实际上是Y字型
     *
     * https://www.nowcoder.com/practice/6ab1d9a29e88450685099d45c9e31e46?tpId=13&tqId=11189&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        int n1 = 0;
        int n2 = 0;
        //先计算两个链表长度
        while (p1 != null) {
            n1++;
            p1 = p1.next;
        }
        while (p2 != null) {
            n2++;
            p2 = p2.next;
        }

        p1 = pHead1; p2 = pHead2;
        //先走多出来的那部分长度
        if(n1 > n2){
            for (int i = 0; i < n1-n2; i++) p1 = p1.next;
        }else {
            for (int i = 0; i < n2-n1; i++) p2 = p2.next;
        }

        //最后一定会相交或者为null
        while (p1 != p2) {
            p1 = p1.next; p2 = p2.next;
        }
        return p1;
    }
}

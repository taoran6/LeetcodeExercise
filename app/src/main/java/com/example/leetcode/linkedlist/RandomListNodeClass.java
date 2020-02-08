package com.example.leetcode.linkedlist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 复杂链表的复制
 *
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回
 * 结果为复制后复杂链表的head。
 */
public class RandomListNodeClass {


    /**
     * 方法一：使用Hash表存储对应关系
     * @param pHead
     * @return
     */
    public RandomListNode Clone1(RandomListNode pHead) {
        RandomListNode ansHead = new RandomListNode(0);
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();

        RandomListNode p = pHead;
        RandomListNode index = ansHead;
        while (p != null) {
            index.next = new RandomListNode(p.label);
            index = index.next;
            map.put(p, index);
            p = p.next;
        }

        p = pHead;
        index = ansHead.next;
        while (p != null) {
            if(p.random != null) {
                index.random = map.get(p.random);
            }
            p = p.next;
            index = index.next;
        }


        return ansHead.next;
    }

    /**
     * 方法二：next指针关联
     * 1.把复制的结点链接在原始链表的每一对应结点后面
     * 2.把复制的结点的random指针指向被复制结点的random指针的下一个结点
     * 3.拆分成两个链表，奇数位置为原链表，偶数位置为复制链表，注意复制链表的最后一个结点的next指针不能跟原链表
     *   指向同一个空结点None，next指针要重新赋值None(判定程序会认定你没有完成复制）
     * @param pHead
     * @return
     */
    public RandomListNode Clone2(RandomListNode pHead) {

        RandomListNode p = pHead;
        while (p != null) {
            RandomListNode newNode = new RandomListNode(p.label);
            newNode.next = p.next;
            p.next = newNode;
            p = newNode.next;
        }

        p = pHead;
        while (p != null) {
            //这里要判空
            if(p.random != null) p.next.random = p.random.next;
            p = p.next.next;
        }

        RandomListNode ans = new RandomListNode(0);
        RandomListNode index = ans;
        p = pHead;
        while (p != null) {
            index.next = p.next;
            index = index.next;
            p.next = p.next.next;
            p = p.next;
            index.next = null;
        }
        return ans.next;
    }
}

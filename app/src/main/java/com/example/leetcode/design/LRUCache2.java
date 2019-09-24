package com.example.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU缓存机制,自己实现，HashMap + 双链表
 */
public class LRUCache2 {
    private int mCapacity;
    private Map<Integer, LinkedNode> map;
    private LinkedNode tail, head;

    public LRUCache2(int capacity) {
        mCapacity = capacity;
        map = new HashMap<>(capacity);
        head = new LinkedNode(0, 0, null, null);
        tail = new LinkedNode(0,0, head, null);
        head.next = tail;
    }

    public int get(int key) {
        if(map.containsKey(key)) {
            LinkedNode node = map.get(key);
            removeLinkedNode(node);
            addLinkedNodeToTail(node);
            return map.get(key).value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            LinkedNode node = map.get(key);
            removeLinkedNode(node);
        }else if(map.size() == mCapacity) {
            removeHead();
        }
        LinkedNode newNode = new LinkedNode(key, value, null, null);
        addLinkedNodeToTail(newNode);
        map.put(key, newNode);
    }

    private void addLinkedNodeToTail(LinkedNode node) {
        node.next = tail;
        node.pre = tail.pre;
        tail.pre = node;
        node.pre.next = node;
    }

    private void removeLinkedNode(LinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void removeHead() {
        map.remove(head.next.key);
        removeLinkedNode(head.next);
    }

    class LinkedNode{
        int key;
        int value;
        LinkedNode pre;
        LinkedNode next;

        public LinkedNode(int key, int value, LinkedNode pre, LinkedNode next) {
            this.key = key;
            this.value = value;
            this.pre = pre;
            this.next = next;
        }
    }
}

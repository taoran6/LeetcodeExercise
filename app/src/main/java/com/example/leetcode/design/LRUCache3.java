package com.example.leetcode.design;

import java.util.concurrent.ConcurrentHashMap;

/**
 * LRUCache的实现
 *
 * 考虑扩展性和并发的场景
 *
 * @param <K> key的类型
 * @param <V> value的类型
 */
public class LRUCache3<K, V> {
    private int mSize;
    // 考虑并发情况，使用ConcurrentHashMap
    private ConcurrentHashMap<K, Node> mMap;
    private Node mTail;
    private Node mHead;

    public LRUCache3 (int inputSize) {
        if(inputSize <= 0) {
            throw new IllegalArgumentException("LRUCache 的容量有误:" + inputSize);
        }
        mMap = new ConcurrentHashMap<>(inputSize);
        mSize = inputSize;
        //构造两个空的头尾节点
        mHead = new Node();
        mTail = new Node();
        mHead.mNext = mTail;
        mTail.mPre = mHead;
    }

    public V get(K key) {
        if(key == null) {
            throw new RuntimeException("Key或Value 不能为空");
        }
        if(mMap.containsKey(key)) {
            //存在对应的数据，需要将节点移到链表头部
            Node node = mMap.get(key);
            synchronized (this) {
                removeNode(node);
                addHead(node);
            }
            return (V)node.mValue;
        } else {
            return null;
        }
    }

    /**
     * 向链表头部增加节点
     * @param node
     */
    private void addHead(Node node) {
        mHead.mNext.mPre = node;
        node.mNext = mHead.mNext;
        mHead.mNext = node;
        node.mPre = mHead;
    }

    /**
     * 删除链表中的node节点
     * @param node
     */
    private void removeNode(Node node) {
        node.mPre.mNext = node.mNext;
        node.mNext.mPre = node.mPre;
    }

    /**
     * 删除链表尾部节点
     */
    private void removeTail() {
        Node removeNode = mTail.mPre;
        removeNode(removeNode);
        mMap.remove(removeNode.mKey);
    }

    public void put(K key, V value) {
        if(key == null || value == null) {
            throw new RuntimeException("Key或Value 不能为空");
        }
        synchronized (this) {
            if(mMap.containsKey(key)) {
                // 已存在对应的key，需要调整节点在链表中的位置
                Node current = mMap.get(key);
                current.mValue = value;
                removeNode(current);
            } else if(mMap.size() == mSize) {
                //超出缓存容量上限，移除链表尾部数据，该数据为最近最少使用
                removeTail();
            }
            Node newNode = new Node(key, value);
            addHead(newNode);
            mMap.put(key, newNode);
        }
    }

    /**
     * 双链表的节点
     */
    private static class Node<K, V> {
        K mKey;
        V mValue;
        Node mNext;
        Node mPre;

        public Node() {

        }

        public Node(K key, V value) {
            mValue = value;
            mKey = key;
            mNext = null;
            mPre = null;
        }
    }
}

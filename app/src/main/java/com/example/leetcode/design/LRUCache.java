package com.example.leetcode.design;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU缓存机制 使用LinkedHashMap
 */
public class LRUCache extends LinkedHashMap<Integer, Integer> {
    /**
     *
     * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作：
     * 获取数据 get 和 写入数据 put 。
     *
     * 获取数据 get(mKey) - 如果密钥 (mKey) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
     * 写入数据 put(mKey, mValue) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之
     * 前删除最近最少使用的数据值，从而为新的数据值留出空间。
     *
     * 进阶:
     *
     * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
     *
     * 示例:
     *
     * LRUCache cache = new LRUCache( 2 /* 缓存容量  )
     * cache.put(1,1);
     * cache.put(2,2);
     * cache.get(1);       // 返回  1
     * cache.put(3,3);    // 该操作会使得密钥 2 作废
     * cache.get(2);       // 返回 -1 (未找到)
     * cache.put(4,4);    // 该操作会使得密钥 1 作废
     * cache.get(1);       // 返回 -1 (未找到)
     * cache.get(3);       // 返回  3
     * cache.get(4);       // 返回  4
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lru-cache
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */
    private int mCapacity;
    public LRUCache(int capacity) {
        //LinkedHashMap是有序的，且默认false为插入顺序，这里使用true访问顺序
        super(capacity, 0.75f, true);
        mCapacity = capacity;

    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    /**
     * 在将新条目插入到映射后，调用put 和 putAll的时候将调用此方法。该方法可以提供在每次添加新条目时移除最旧条目的实现程序，
     * 默认返回false,在此处重写，当大于mCapacity个条目的时候删除最旧的条目。
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > mCapacity;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(mKey);
 * obj.put(mKey,mValue);
 */

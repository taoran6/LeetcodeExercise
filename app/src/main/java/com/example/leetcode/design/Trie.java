package com.example.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现 Trie (前缀树)
 */
public class Trie {
    /**
     * 与哈希表相比，Trie 树在存储多个具有相同前缀的键时可以使用较少的空间。此时 Trie 树只需要 O(m) 的时间复杂
     * 度，其中 m 为键长。而在平衡树中查找键值需要 O(mlogn) 时间复杂度。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    private class TrieNode {
        char ch;        //其实这个字段可以去掉
        Map<Character, TrieNode> children;
        boolean isEnd;

        TrieNode (char ch) {
            this.ch = ch;
            children = new HashMap<>();
            isEnd = false;
        }
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode(' ');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] charArray = word.toCharArray();
        TrieNode pointer = root;
        for (char c : charArray) {
            if(!pointer.children.containsKey(c)) {
                pointer.children.put(c, new TrieNode(c));
            }
            pointer = pointer.children.get(c);
        }
        pointer.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] charArray = word.toCharArray();
        TrieNode pointer = root;
        for (char c : charArray) {
            if(!pointer.children.containsKey(c)) return false;
            else pointer = pointer.children.get(c);
        }
        return pointer.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        char[] charArray = prefix.toCharArray();
        TrieNode pointer = root;
        for (char c : charArray) {
            if(!pointer.children.containsKey(c)) return false;
            else pointer = pointer.children.get(c);
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

package com.example.leetcode;

import android.util.LruCache;

import com.example.leetcode.DP.LongestSubString;
import com.example.leetcode.DP.MincostTickets;
import com.example.leetcode.design.LRUCache;
import com.example.leetcode.design.LRUCache3;
import com.example.leetcode.string.BoldWords;
import com.example.leetcode.tree.BuildTree;
import com.example.leetcode.tree.ConvertBST;
import com.example.leetcode.tree.CreateNode;
import com.example.leetcode.tree.TreeNode;

import java.util.Stack;

/**
 * 测试用
 */
public class MainTest {
    public static void main(String[] args) {
        //这里写测试用例
        TreeNode root = new CreateNode().createNode(new Integer[]{5,3,6,2,4,null,7});
        new ConvertBST().deleteNode(root, 3);
    }

    private static void testCase1() {
        boolean flag = true;
        LRUCache3<Integer, Integer> cache = new LRUCache3<>(2);
        cache.put(1, 1);
        cache.put(2, 2);
        flag = (cache.get(1) == 1) && flag;         // 返回  1
        cache.put(3, 3);                            // 该操作会使得密钥 2 作废
        flag = (cache.get(2) == null) && flag;      // 返回 null (未找到)
        cache.put(4, 4);                            // 该操作会使得密钥 1 作废
        flag = (cache.get(1) == null) && flag;      // 返回 null (未找到)
        flag = (cache.get(3) == 3) && flag;         // 返回  3
        flag = (cache.get(4) == 4) && flag;         // 返回  4
        assert (flag);
    }

    private static void testCase2() {
        boolean flag = false;
        try {
            // 对容量是负数的测试
            LRUCache3<Integer, Integer> cache = new LRUCache3<>(-1);
        } catch (Exception e) {
            if(e instanceof IllegalArgumentException) {
                flag = true;
            }
        } finally {
            assert (flag);
        }
    }

    private static void testCase3() {
        boolean flag = false;
        try {
            LRUCache3<Integer, Integer> cache = new LRUCache3<>(3);
            cache.put(1, null);
            cache.put(null, 1);
        } catch (Exception e) {
            if(e instanceof RuntimeException) {
                flag = true;
            }
        } finally {
            assert (flag);
        }
    }

    private static void testCase4() {
        boolean flag = false;
        try {
            LRUCache3<Integer, Integer> cache = new LRUCache3<>(3);
            cache.put(1, 1);
            cache.get(null);
        } catch (Exception e) {
            if(e instanceof RuntimeException) {
                flag = true;
            }
        } finally {
            assert (flag);
        }
    }


}

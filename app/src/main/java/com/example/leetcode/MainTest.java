package com.example.leetcode;

import com.example.leetcode.array.FindDisappearedNumbers;
import com.example.leetcode.compute.HammingDistance;
import com.example.leetcode.tree.CreateNode;
import com.example.leetcode.tree.PathSum;
import com.example.leetcode.tree.TreeNode;

/**
 * 测试用
 */
public class MainTest {
    public static void main(String[] args) {
        int[] nums = new int[]{4,3,2,7,8,2,3,1};
        System.out.println(new FindDisappearedNumbers().findDisappearedNumbers(nums));

    }
}

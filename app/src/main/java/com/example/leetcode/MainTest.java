package com.example.leetcode;

import com.example.leetcode.DP.CanJump;
import com.example.leetcode.DP.UniquePaths;
import com.example.leetcode.array.NextPermutation;
import com.example.leetcode.array.Rotate;
import com.example.leetcode.backtracking.CombinationSum;
import com.example.leetcode.graph.NumIslands;
import com.example.leetcode.tree.InorderTraversal;
import com.example.leetcode.tree.TreeNode;

/**
 * 测试用
 */
public class MainTest {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.right = node2;
        node2.left = node3;
        new InorderTraversal().inorderTraversal(node1);
    }
}

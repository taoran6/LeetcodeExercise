package com.example.leetcode;

import com.example.leetcode.compute.HammingDistance;
import com.example.leetcode.tree.CreateNode;
import com.example.leetcode.tree.PathSum;
import com.example.leetcode.tree.TreeNode;

/**
 * 测试用
 */
public class MainTest {
    public static void main(String[] args) {
        Integer[] tree = new Integer[]{10,5,-3,3,2,null,11,3,-2,null,1};
        TreeNode root = new CreateNode().createNode(tree);
        System.out.println(new PathSum().pathSum(root, 8));

    }
}

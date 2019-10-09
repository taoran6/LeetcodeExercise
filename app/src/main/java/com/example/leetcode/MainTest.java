package com.example.leetcode;
import com.example.leetcode.tree.LowestCommonAncestor;
import com.example.leetcode.tree.TreeNode;

/**
 * 测试用
 */
public class MainTest {
    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);
        node3.left = node5; node3.right = null;
        node5.left = node6; node5.right = node2;
        node2.left = node7; node2.right = node4;
        System.out.println(new LowestCommonAncestor().lowestCommonAncestorTree(node3, node4, node7));
    }
}

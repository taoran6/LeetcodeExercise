package com.example.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的最大深度
 */
public class MaxDepth {
    /**
     * 给定一个二叉树，找出其最大深度。
     *
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;

        return Math.max(maxDepth(root.right), maxDepth(root.left)) + 1;
    }

    /**
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例:
     *
     * 输入:
     *
     *    1
     *  /   \
     * 2     3
     *  \
     *   5
     *
     * 输出: ["1->2->5", "1->3"]
     *
     * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-paths
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if(root == null) return list;

        String curr = root.val + "";
        if(root.left == null && root.right == null) {
            list.add(curr);
        }
        if(root.left != null) binaryTreePaths(root.left, list, curr);
        if(root.right != null) binaryTreePaths(root.right, list, curr);
        return list;
    }

    private void binaryTreePaths(TreeNode root, List<String> list, String pre) {
        String curr = pre + "->" + root.val;
        if(root.left == null && root.right == null) {
            list.add(curr);
        }
        if(root.left != null) binaryTreePaths(root.left, list, curr);
        if(root.right != null) binaryTreePaths(root.right, list, curr);
    }

}

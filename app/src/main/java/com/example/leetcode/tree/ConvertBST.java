package com.example.leetcode.tree;

/**
 * 把二叉搜索树转换为累加树
 */
public class ConvertBST {
    /**
     * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的
     * 节点值加上所有大于它的节点值之和。
     *
     * 例如：
     *
     * 输入: 二叉搜索树:
     *               5
     *             /   \
     *            2     13
     *
     * 输出: 转换为累加树:
     *              18
     *             /   \
     *           20     13
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public TreeNode convertBST(TreeNode root) {
        convertBST(root, 0);
        return root;
    }

    // 逆中序遍历
    public int convertBST(TreeNode root, int count) {       //count用于存储累加值
        if (root != null) {
            count = convertBST(root.right, count);
            count = count + root.val;
            root.val = count;
            count = convertBST(root.left, count);
        }
        return count;
    }
}

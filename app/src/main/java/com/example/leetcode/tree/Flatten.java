package com.example.leetcode.tree;


/**
 * 114. 二叉树展开为链表
 */
public class Flatten {
    /**
     * 给定一个二叉树，原地将它展开为链表。
     *
     * 例如，给定二叉树
     *
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     * 将其展开为：
     *
     * 1
     *  \
     *   2
     *    \
     *     3
     *      \
     *       4
     *        \
     *         5
     *          \
     *           6
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 获得其前序遍历
     */
    private TreeNode current;
    public void flatten(TreeNode root) {
        if(root == null) return;
        current = root;

        /*先整理根节点*/
        TreeNode right = root.right;
        TreeNode left = root.left;
        root.right = left;
        //这里不要忘记另左子树为null
        root.left = null;

        //递归左子树
        flatten(left);
        //此时current指向左子树的最后一个节点，把右子树接到后面
        current.right = right;
        //递归右子树
        flatten(right);
    }
}

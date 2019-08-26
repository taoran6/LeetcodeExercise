package com.example.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 对称二叉树
 */
public class SymmetricTree {
    /**
     * 给定一个二叉树，检查它是否是镜像对称的。
     *
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     *
     *     1
     *    / \
     *   2   2
     *    \   \
     *    3    3
     * 说明:
     *
     * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/symmetric-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public boolean isSubSymmetric(TreeNode root) {
        //方法一：使用递归，需要新写一个递归方法
        if (root == null) return true;
        return isSubSymmetric(root.left, root.right);

    }

    /**
     * 递归方法调用的方法
     * @param t1
     * @param t2
     * @return
     */
    private boolean isSubSymmetric(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        else if(t1 == null || t2 == null || t1.val != t2.val) return false;
        return isSubSymmetric(t1.left, t2.right) && isSubSymmetric(t1.right, t2.left);
    }

    /**
     * 迭代方法
     * @param root
     * @return
     */
    public boolean isSubSymmetric2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (! queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if(t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null || t1.val != t2.val) return false;
            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }
        return true;
    }
}

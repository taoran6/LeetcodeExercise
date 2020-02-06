package com.example.leetcode.tree;


/**
 * 树的子结构
 */
public class HasSubtree {
    /**
     * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
     */
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root1 == null || root2 == null) return false;

        if(root1.val == root2.val && findSub(root1, root2)) return true;

        else return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);

    }

    private boolean findSub(TreeNode root1, TreeNode root2) {
        if(root2 == null) return true;
        else if(root1 == null) return false;

        else return (root1.val == root2.val) && findSub(root1.left, root2.left)
                    && findSub(root1.right, root2.right);
    }
}

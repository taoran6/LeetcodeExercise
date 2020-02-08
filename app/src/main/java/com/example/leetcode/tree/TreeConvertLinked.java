package com.example.leetcode.tree;

import java.util.List;

/**
 * 二叉搜索树与双向链表
 */
public class TreeConvertLinked {
    /**
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指
     * 针的指向。
     * @param pRootOfTree
     * @return
     *
     * 方法一：创建一个数组
     */
    public TreeNode Convert1(TreeNode pRootOfTree) {
        TreeNode[] ret = help(pRootOfTree);
        return ret[0];
    }

    private TreeNode[] help(TreeNode root) {
        TreeNode[] ret = new TreeNode[2];
        if(root == null) return ret;
        TreeNode[] left = help(root.left);
        TreeNode[] right = help(root.right);
        if(left[0] != null) {
            root.left = left[1];
            //这里需要注意赋值
            left[1].right = root;
            ret[0] = left[0];
        } else {
            root.left = null;
            ret[0] = root;
        }

        if(right[0] != null) {
            root.right = right[0];
            //这里需要注意赋值
            right[0].left = root;
            ret[1] = right[1];
        } else {
            root.right = null;
            ret[1] = root;
        }

        return ret;
    }

    /**
     * 方法二：中序遍历即可
     */
    private TreeNode head = null;
    private TreeNode preNode = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        midOrder(pRootOfTree);
        return head;
    }

    private void midOrder(TreeNode root) {
        if(root == null) return;
        midOrder(root.left);
        if(head == null) {
            head = root;
            preNode = root;
        } else {
            root.left = preNode;
            preNode.right = root;
            preNode = root;
        }
        midOrder(root.right);
    }
}

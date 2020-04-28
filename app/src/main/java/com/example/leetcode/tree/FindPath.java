package com.example.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 微软面试题，求二叉树根节点到叶子节点路径的和最大的一条路径，返回该路径的list
 */
public class FindPath {
    //存储当前最大路径
    private List<TreeNode> ans;
    //存储当前最大的和
    private int sum = 0;
    public List<TreeNode> findPath(TreeNode root) {

        if(root == null) return new ArrayList<>();
        findPathHelp(root, new ArrayList<>(), 0);
        return ans == null ? new ArrayList<>() : ans;
    }

    private void findPathHelp(TreeNode root, List<TreeNode> list, int currentSum) {
        list.add(root);
        int tmp = currentSum + root.val;
        // 叶子节点
        if(root.left == null && root.right == null) {
            if(tmp > sum) {
                ans = new ArrayList<>(list);
                sum = tmp;
            }
        } else {
            if(root.left != null){
                findPathHelp(root.left, list, tmp);
            }
            if(root.right != null) {
                findPathHelp(root.right, list, tmp);
            }
        }
        list.remove(list.size() - 1);
    }
}

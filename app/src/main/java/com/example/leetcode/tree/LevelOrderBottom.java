package com.example.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层次遍历
 */
public class LevelOrderBottom {
    /**
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     *
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其自底向上的层次遍历为：
     *
     * [
     *   [15,7],
     *   [9,20],
     *   [3]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：使用列表存储上一层节点
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;


        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        ans.add(new ArrayList<Integer>(){{add(root.val);}});

        List<TreeNode> lastList = list;     //保存上一层节点的列表
        while (!lastList.isEmpty()) {
            List<TreeNode> nextList = new ArrayList<>();
            List<Integer> ansList = new ArrayList<>();
            for (int i = 0; i < lastList.size(); i ++) {
                if(lastList.get(i).left != null) {
                    nextList.add(lastList.get(i).left);
                    ansList.add(lastList.get(i).left.val);
                }
                if(lastList.get(i).right != null) {
                    nextList.add(lastList.get(i).right);
                    ansList.add(lastList.get(i).right.val);
                }
            }
            ans.add(0, ansList);
            lastList = nextList;
        }

        ans.remove(0);
        return ans;
    }

    /**
     * 方法二：使用队列
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();    //size保存了该层的节点个数
            List<Integer> list = new ArrayList<>();
            while (size != 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
                size--;
            }
            ans.add(0, list);
        }

        return ans;
    }

    /**
     * 方法三：递归
     */
    public List<List<Integer>> levelOrderBottom3(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        DFS(root, 0, ans);
        return ans;
    }

    private void DFS(TreeNode root, int level, List<List<Integer>> ans) {
        //当前层数还没有元素，先 new 一个空的列表
        if(ans.size() - 1 < level) {
            ans.add(0, new ArrayList<>());
        }

        ans.get(ans.size() - level - 1).add(root.val);

        if(root.left != null) DFS(root.left, level + 1, ans);
        if(root.right != null) DFS(root.right, level + 1, ans);
    }

}

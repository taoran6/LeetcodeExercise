package com.example.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
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

    /**
     * 二叉树的最小深度
     *
     * 给定一个二叉树，找出其最小深度。
     *
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例:
     *
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最小深度  2.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;  // 当前访问层数

        //基于上面的levelOrderBottom2(TreeNode root)方法，使用队列
        while (!queue.isEmpty()) {
            int size = queue.size();       //该层节点数
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node.left == null && node.right == null) return level;
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            level ++;
        }
        return level;
    }

    /**
     * 二叉树的层次遍历
     *
     * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     *
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;


        List<TreeNode> lastNodeList = new ArrayList<TreeNode>() {{
            add(root);
        }};
        ans.add(new ArrayList<Integer>() {{
            add(root.val);
        }});

        while (!lastNodeList.isEmpty()) {
            List<TreeNode> nextNodeList = new ArrayList<>();
            List<Integer> nextAns = new ArrayList<>();

            for (int i = 0; i < lastNodeList.size(); i++) {
                TreeNode node = lastNodeList.get(i);
                if (node.left != null) {
                    nextNodeList.add(node.left);
                    nextAns.add(node.left.val);
                }
                if (node.right != null) {
                    nextNodeList.add(node.right);
                    nextAns.add(node.right.val);
                }
            }
            ans.add(nextAns);
            lastNodeList = nextNodeList;
        }

        ans.remove(ans.size() - 1);
        return ans;
    }

    /**
     * 103. 二叉树的锯齿形层次遍历
     *
     * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层
     * 之间交替进行）。
     *
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回锯齿形层次遍历如下：
     *
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        //整个思路跟层次遍历差不多，就是多用了一个标志位判断需不需要反转
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isReverse = false;

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
            if(isReverse) {
                Collections.reverse(list);
            }
            isReverse = ! isReverse;
            ans.add(list);
        }

        return ans;
    }

}

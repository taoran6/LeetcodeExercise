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

    /**
     * 124. 二叉树中的最大路径和
     *
     * 给定一个非空二叉树，返回其最大路径和。
     *
     * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
     *
     * 示例 1:
     *
     * 输入: [1,2,3]
     *
     *        1
     *       / \
     *      2   3
     *
     * 输出: 6
     * 示例 2:
     *
     * 输入: [-10,9,20,null,null,15,7]
     *
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 输出: 42
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    int maxSum = Integer.MIN_VALUE;     //全局变量保存最大路径和
    public int maxPathSum(TreeNode root) {
        onSideMax(root);
        return maxSum;
    }

    /**
     * 递归计算包含以根为起点的最大路径
     *
     * 核心：后序遍历
     *
     * @param root
     * @return 返回以根为起点的最大路径和
     */
    private int onSideMax(TreeNode root) {
        if(root == null) return 0;

        int leftMax = onSideMax(root.left);
        int rightMax = onSideMax(root.right);

        //包含左右孩子节点的最大路径，取最大值
        int maxChild = Math.max(leftMax, rightMax);
        // maxChild 可能为负值，此时只有root节点是最大路径
        int maxSide = Math.max(root.val, root.val + maxChild);
        // 更新最大路径，leftMax + rightMax + root.val是穿过根节点的最大路径
        maxSum = Math.max(maxSum, Math.max(maxSide, leftMax + rightMax + root.val));
        return maxSide;
    }
}

package com.example.leetcode.tree;

/**
 * 二叉搜索树中第K小的元素
 */
public class KthSmallest {
    /**
     * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     *
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     *
     * 示例 1:
     *
     * 输入: root = [3,1,4,null,2], k = 1
     *    3
     *   / \
     *  1   4
     *   \
     *    2
     * 输出: 1
     * 示例 2:
     *
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     *        5
     *       / \
     *      3   6
     *     / \
     *    2   4
     *   /
     *  1
     * 输出: 3
     * 进阶：
     * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    private int count = 1;
    private int ans = 0;
    public int kthSmallest(TreeNode root, int k) {
        findKthNode(root, k);
        return ans;
    }

    private boolean findKthNode(TreeNode root, int k) {
        if(root == null) return false;
        boolean isFind = findKthNode(root.left, k);
        if(isFind) return true;     //提前减枝
        if (count ++ == k) {
            ans = root.val;
            return true;       //提前减枝
        }
        return findKthNode(root.right, k);
    }

    /**
     * 二叉搜索树的范围和
     *
     * 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
     *
     * 二叉搜索树保证具有唯一的值。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
     * 输出：32
     * 示例 2：
     *
     * 输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
     * 输出：23
     *  
     *
     * 提示：
     *
     * 树中的结点数量最多为 10000 个。
     * 最终的答案保证小于 2^31。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/range-sum-of-bst
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    private int rangeSum = 0;
    public int rangeSumBST(TreeNode root, int L, int R) {
        middleOrder(root, L, R);
        return rangeSum;
    }

    private void middleOrder(TreeNode root, int L, int R) {
        if(root == null) return;
        if(root.val >= L) {
            middleOrder(root.left, L, R);
            if(root.val > R) return;
            else rangeSum += root.val;
        }
        middleOrder(root.right, L, R);
    }
}

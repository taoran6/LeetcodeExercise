package com.example.leetcode.tree;

import java.util.Map;
import java.util.Stack;

/**
 * 平衡二叉树
 */
public class BalancedTree {
    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     *
     * 本题中，一棵高度平衡二叉树定义为：
     *
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
     *
     * 示例 1:
     *
     * 给定二叉树 [3,9,20,null,null,15,7]
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回 true 。
     *
     * 示例 2:
     *
     * 给定二叉树 [1,2,2,3,3,null,null,4,4]
     *
     *        1
     *       / \
     *      2   2
     *     / \
     *    3   3
     *   / \
     *  4   4
     * 返回 false 。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 对二叉树做深度优先遍历DFS，递归过程中：
     * 终止条件：当DFS越过叶子节点时，返回高度0；
     * 返回值：
     * 从底至顶，返回以每个节点root为根节点的子树最大高度(左右子树中最大的高度值加1max(left,right) + 1)；
     * 当我们发现有一例 左/右子树高度差 ＞ 1 的情况时，代表此树不是平衡树，返回-1；
     * 当发现不是平衡树时，后面的高度计算都没有意义了，因此一路返回-1，避免后续多余计算。
     * 最差情况是对树做一遍完整DFS，时间复杂度为 O(N)。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/balanced-binary-tree/solution/balanced-binary-tree-di-gui-fang-fa-by-jin40789108/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    /**
     * 核心是用-1表示非平衡并提前结束，非零则表示树的高度，用一种返回值表示两种含义
     * @param root
     * @return
     */
    private int getHeight(TreeNode root) {
        if(root == null) return 0;
        int leftH = getHeight(root.left);
        if (leftH == -1) return -1;
        int rightH = getHeight(root.right);
        if(rightH == -1) return -1;
        return Math.abs(leftH - rightH) > 1 ? -1 : Math.max(rightH, leftH) + 1;
    }


    /**
     * 验证二叉搜索树
     *
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *
     * 假设一个二叉搜索树具有如下特征：
     *
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 示例 1:
     *
     * 输入:
     *     2
     *    / \
     *   1   3
     * 输出: true
     * 示例 2:
     *
     * 输入:
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *      根节点的值为 5 ，但是其右子节点值为 4 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：递归
     */
    public boolean isValidBST(TreeNode root) {
        /**
         * 这里有两点需要注意的
         *
         * 1. 需要保存最大值和最小值，如果只是单纯的判断根节点与左右孩子节点大小，对于
         *     5
         *    / \
         *   1   6
         *      / \
         *     3   7
         * 这样的例子本来应该返回false （3 < 5），却返回了true。
         *
         * 2. 为了防止溢出[2147483647]，需要使用 Long 类型。
         */
        return isValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    private boolean isValidBST(TreeNode root, long max, long min) {
        if(root == null) return true;
        if(root.val >= max || root.val <= min) return false;
        return isValidBST(root.left, root.val, min) && isValidBST(root.right, max, root.val);
    }

    /**
     * 方法二：迭代，深度优先遍历
     */
    public boolean isValidBST2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        long pre = Long.MIN_VALUE;      //同样这里需要 long
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            if(current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                if(current.val <= pre) return false;
                pre = current.val;
                current = current.right;
            }
        }
        return true;
    }

}

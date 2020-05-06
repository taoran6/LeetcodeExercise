package com.example.leetcode.tree;

/**
 * 二叉搜索树的后序遍历序列
 */
public class VerifySquenceOfBST {
    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数
     * 组的任意两个数字都互不相同。
     */
    public boolean VerifySquenceOfBST(int [] sequence) {
        //牛客网这个题目表述贼坑，也不说清楚长度为0满足不满足，单独拿出来才通过提交
        if(sequence.length == 0) return false;

        return help(sequence, 0, sequence.length - 1);
    }

    private boolean help(int[] sequence, int start, int end) {
        if(start >= end) return true;
        int root = sequence[end];

        int mid = end;
        for(int i = start; i < end; i++) {
            if(sequence[i] > root) {
                mid = i;
                break;
            }
        }

        for (int i = mid + 1; i < end; i++){
            if(sequence[i] <= root) return false;
        }

        return help(sequence, start, mid - 1) && help(sequence, mid, end - 1);
    }

    /**
     * 99. 恢复二叉搜索树
     *
     * 二叉搜索树中的两个节点被错误地交换。
     *
     * 请在不改变其结构的情况下，恢复这棵树。
     *
     * 示例 1:
     *
     * 输入: [1,3,null,null,2]
     *
     *    1
     *   /
     *  3
     *   \
     *    2
     *
     * 输出: [3,1,null,null,2]
     *
     *    3
     *   /
     *  1
     *   \
     *    2
     * 示例 2:
     *
     * 输入: [3,1,4,null,null,2]
     *
     *   3
     *  / \
     * 1   4
     *    /
     *   2
     *
     * 输出: [2,1,4,null,null,3]
     *
     *   2
     *  / \
     * 1   4
     *    /
     *   3
     * 进阶:
     *
     * 使用 O(n) 空间复杂度的解法很容易实现。
     * 你能想出一个只使用常数空间的解决方案吗？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 核心思想：中序遍历
     * 14325 ->
     * 12345
     *
     * 有两个乱序序列：43 和 32
     *
     * 可以看到首先不一致的那个前驱节点肯定是需要交换的，如43中的4，然后接着遍历找到第二个不一致的序列，如32，
     * 此时要交换的是后面的2.
     *
     * 这里存在一种情况是只有一个乱序序列 如1324，两种情况可合并考虑
     * 时间复杂度O(n)
     * 空间复杂度O(H)即递归栈的深度
     */

    // s和t代表要交换的节点
    TreeNode startNode = null;
    TreeNode endNode = null;

    //前驱节点
    TreeNode pre = null;

    public void recoverTree(TreeNode root) {
        findSwapNode(root);

        //交换val数值
        int tmp = startNode.val;
        startNode.val = endNode.val;
        endNode.val = tmp;
    }

    private void findSwapNode(TreeNode root) {
        if(root == null) return;
        findSwapNode(root.left);

        if(pre != null && pre.val > root.val) {
            if(startNode != null) {
                //找到第一个不一致的序列
                startNode = pre;
                endNode = root;     //这里存在一种情况是只有一个乱序序列，则start和end都找到了，否则会在后面跟新endNode的，不用担心
            } else {
                //找到第二个不一致的序列
                endNode = root;
                return;
            }
        }
        pre = root;

        findSwapNode(root.right);
    }

    /**
     * TODO 方法二：Morris遍历
     */
}

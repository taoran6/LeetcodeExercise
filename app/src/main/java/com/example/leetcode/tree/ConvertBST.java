package com.example.leetcode.tree;

/**
 * 把二叉搜索树转换为累加树
 */
public class ConvertBST {
    /**
     * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的
     * 节点值加上所有大于它的节点值之和。
     *
     * 例如：
     *
     * 输入: 二叉搜索树:
     *               5
     *             /   \
     *            2     13
     *
     * 输出: 转换为累加树:
     *              18
     *             /   \
     *           20     13
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public TreeNode convertBST(TreeNode root) {
        convertBST(root, 0);
        return root;
    }

    // 逆中序遍历
    public int convertBST(TreeNode root, int count) {       //count用于存储累加值
        if (root != null) {
            count = convertBST(root.right, count);
            count = count + root.val;
            root.val = count;
            count = convertBST(root.left, count);
        }
        return count;
    }

    /**
     * 701. 二叉搜索树中的插入操作
     *
     * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。
     *
     * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
     *
     * 例如, 
     *
     * 给定二叉搜索树:
     *
     *         4
     *        / \
     *       2   7
     *      / \
     *     1   3
     *
     * 和 插入的值: 5
     * 你可以返回这个二叉搜索树:
     *
     *          4
     *        /   \
     *       2     7
     *      / \   /
     *     1   3 5
     * 或者这个树也是有效的:
     *
     *          5
     *        /   \
     *       2     7
     *      / \
     *     1   3
     *          \
     *           4
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        return insertHelp(root, node);
    }

    private TreeNode insertHelp(TreeNode root, TreeNode node) {
        if(root == null) return node;
        if(root.val < node.val) {
            root.right = insertHelp(root.right, node);
        }else{
            root.left = insertHelp(root.left, node);
        }
        return root;
    }
}

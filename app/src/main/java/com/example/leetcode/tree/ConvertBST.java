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
    public TreeNode insertIntoBST1(TreeNode root, int val) {
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

    /**
     * 方法二：简洁的写法
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);

        if(root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    /**
     * 450. 删除二叉搜索树中的节点
     *
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质
     * 不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     *
     * 一般来说，删除节点可分为两个步骤：
     *
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
     * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
     *
     * 示例:
     *
     * root = [5,3,6,2,4,null,7]
     * key = 3
     *
     *     5
     *    / \
     *   3   6
     *  / \   \
     * 2   4   7
     *
     * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
     *
     * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
     *
     *     5
     *    / \
     *   4   6
     *  /     \
     * 2       7
     *
     * 另一个正确答案是 [5,2,6,null,4,null,7]。
     *
     *     5
     *    / \
     *   2   6
     *    \   \
     *     4   7
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return root;
        if(root.val == key) {
            //该节点没有左子树或/且没有右子树
            if(root.left == null && root.right == null) return null;
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;

            /**左子树和右子树都有的情况*/
            //交换根节点和右子树最小节点的值
            TreeNode swapNode = findLeftNode(root.right);
            root.val = swapNode.val;
            //删除那个右子树最小节点
            root.right = deleteNode(root.right, swapNode.val);
        } else if(key < root.val) {
            // 递归在左子树中删除。注意返回赋值
            root.left = deleteNode(root.left, key);
        } else {
            // 递归在右子树中删除
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    /**
     * 寻找该树最左节点，这个最左节点是BST中最小的节点
     * @param root  已保证非空
     * @return
     */
    private TreeNode findLeftNode(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}

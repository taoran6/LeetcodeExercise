package com.example.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历
 */
public class InorderTraversal {
    /**
     * 给定一个二叉树，返回它的中序 遍历。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,null,2,3]
     *      1
     *       \
     *        2
     *       /
     *      3
     * <p>
     * 输出: [1,3,2]
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 方法一：递归，略
     * <p>
     * 方法二：使用栈,再用一个指针模拟访问过程，这个方法要记住
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                ans.add(current.val);
                current = current.right;
            }
        }
        return ans;
    }

    /**
     * 方法三：莫里斯中序遍历
     * 使用一种新的数据结构：线索二叉树
     * 该方法破坏了树的结构
     *
     * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/er-cha-shu-de-zhong-xu-bian-li-by-leetcode/
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        if(root == null) return null;

        List<Integer> ans = new ArrayList<>();

        TreeNode current = root;
        TreeNode pre;

        while (current != null) {
            if(current.left == null) {
                ans.add(current.val);
                current = current.right;
                continue;
            }

            pre = current.left;
            while (pre.right != null && pre.right != current) {
                pre = pre.right;
            }

            pre.right = current;
            TreeNode tmp = current;
            current = current.left;
            tmp.left = null;
        }

        return ans;
    }
}

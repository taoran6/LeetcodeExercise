package com.example.leetcode.design;

import com.example.leetcode.tree.TreeNode;

import java.util.Stack;

/**
 * 173. 二叉搜索树迭代器
 *
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 *
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 *
 *  
 *
 * 示例：
 *
 *
 *
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // 返回 3
 * iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 9
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 *  
 *
 * 提示：
 *
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-search-tree-iterator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 方法一：先不考虑题目 Note 中要求的空间复杂度和时间复杂度，简单粗暴一些。在构造函数中，对二叉树进行中序遍历，
 * 把结果保存到一个队列中，然后 next 方法直接执行出队操作即可。至于 hasNext 方法的话，判断队列是否为空即可。
 * 略写
 *
 * 方法二：使用栈保存，模拟中序遍历
 * 时间复杂度的话，对于 next 方法，大多数时候是 O(1)，但最坏情况因为最里边的 while 循环，其实有可能达到 O(n)。
 * 但如果算均摊时间复杂度的话，其实还是 O(1)，因为每个节点最多也就经过两次就出栈了。
 * 空间复杂度，这里只需要消耗栈的空间，也就是 O(h)
 *
 * 这种分析方式称为摊还分析，详细的学习可以看看**《算法导论》- 第17章 摊还分析**
 */
public class BSTIterator {
    private Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode out = stack.pop();
        TreeNode pushNode  = out.right;
        while (pushNode != null) {
            stack.push(pushNode);
            pushNode = pushNode.left;
        }
        return out.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * Your BSTIterator object will be instantiated and called as such:
     * BSTIterator obj = new BSTIterator(root);
     * int param_1 = obj.next();
     * boolean param_2 = obj.hasNext();
     */

}

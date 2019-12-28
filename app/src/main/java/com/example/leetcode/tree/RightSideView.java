package com.example.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 */
public class RightSideView {
    /**
     * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     *
     * 示例:
     *
     * 输入: [1,2,3,null,5,null,4]
     * 输出: [1, 3, 4]
     * 解释:
     *
     *    1            <---
     *  /   \
     * 2     3         <---
     *  \     \
     *   5     4       <---
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：广度优先搜索
     */
    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        //这里需要判空一下
        if(root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 1; i < size; i++) {
                TreeNode node = queue.poll();
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            TreeNode ansNode = queue.poll();
            ans.add(ansNode.val);
            if(ansNode.left != null) queue.offer(ansNode.left);
            if(ansNode.right != null) queue.offer(ansNode.right);
        }
        return ans;
    }

    /**
     * 方法二：深度优先搜索
     *
     * 在深度优先搜索中，我们总是先访问右子树。这样就保证了当我们访问树的某个特定深度时，我们正在访问的节点总是
     * 该深度的最右侧节点。于是，可以存储在每个深度访问的第一个结点，一旦我们知道了树的层数，就可以得到最终的结
     * 果数组。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view/solution/er-cha-shu-de-you-shi-tu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    private int maxDepth = 0;       //保存已遍历过的最大层数
    private List<Integer> ans;
    public List<Integer> rightSideView2(TreeNode root) {
        ans = new ArrayList<>();
        if(root != null) dfsView(root, 1);
        return ans;
    }

    private void dfsView(TreeNode root, int depth) {
        if(depth > maxDepth){
            ans.add(root.val);
            maxDepth = depth;
        }

        if(root.right != null) dfsView(root.right, depth + 1);
        if(root.left != null) dfsView(root.left, depth + 1);
    }

}

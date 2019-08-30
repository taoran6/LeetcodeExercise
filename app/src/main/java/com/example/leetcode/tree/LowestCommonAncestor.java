package com.example.leetcode.tree;

/**
 *  二叉搜索树的最近公共祖先
 */
public class LowestCommonAncestor {
    /**
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是
     * p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //p或者q 就是根节点
        if(root == p || root == q) return root;
        //p, q在根节点两侧
        if ((root.val > p.val && root.val < q.val) || root.val < p.val && root.val > q.val) return root;
        //p, q在根节点同侧
        return lowestCommonAncestor(root.val > p.val ? root.left : root.right, p, q);
    }
}

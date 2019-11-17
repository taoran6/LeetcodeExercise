package com.example.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 */
public class BuildTree {
    /**
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     *
     * 注意:
     * 你可以假设树中没有重复的元素。
     *
     * 例如，给出
     *
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //这里用HashMap存储所有中序遍历的index可以节省查找时间
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1, indexMap);
    }

    private TreeNode buildTree(int[] preorder, int pStart, int pEnd,
                               int[] inorder, int iStart, int iEnd, Map<Integer, Integer> map) {
        if(pStart > pEnd) return null;
        TreeNode root = new TreeNode(preorder[pStart]);
        //直接使用map而不使用遍历查找
        int index = map.get(preorder[pStart]);
        root.left = buildTree(preorder, pStart + 1, pStart + index - iStart,
                inorder, iStart, index - 1, map);
        root.right = buildTree(preorder, pStart + index - iStart + 1, pEnd,
                inorder, index + 1, iEnd, map);
        return root;
    }
}

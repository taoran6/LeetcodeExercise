package com.example.leetcode.tree;

/**
 * 序列化二叉树
 */
public class SerializeTree {
    /**
     * 请实现两个函数，分别用来序列化和反序列化二叉树
     *
     * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉
     * 树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，
     * 序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。
     *
     * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
     */
    String Serialize(TreeNode root) {
        if(root == null) return "# ";
        StringBuilder builder = new StringBuilder();
        builder.append(root.val).append(" ");
        builder.append(Serialize(root.left));
        builder.append(Serialize(root.right));
        return builder.toString();
    }

    //利用了一下前序遍历的递归性质，使用一个index指针
    int index = -1;
    TreeNode Deserialize(String str) {
        index ++;
        TreeNode node = null;
        String[] array = str.split(" ");
        if(!"#".equals(array[index])) {
            node = new TreeNode(Integer.valueOf(array[index]));
            node.left = Deserialize(str);
            node.right = Deserialize(str);
        }
        return node;
    }
}

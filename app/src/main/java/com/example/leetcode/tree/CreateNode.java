package com.example.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 建立二叉树
 */
public class CreateNode {
    public TreeNode createNode(Integer[] array) {
        if(array == null || array.length == 0) return null;

        List<TreeNode> list = new ArrayList<>();
        for (Integer integer : array) {
            if(integer == null) list.add(null);
            else {
                list.add(new TreeNode(integer));
            }
        }

        for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++) {
            // 左孩子
            if(list.get(parentIndex) != null)
                list.get(parentIndex).left = list.get(parentIndex * 2 + 1);
            // 右孩子
            if(list.get(parentIndex) != null)
                list.get(parentIndex).right = list.get(parentIndex * 2 + 2);
        }
        // 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理
        int lastParentIndex = array.length / 2 - 1;

        if(list.get(lastParentIndex) != null) {
            // 左孩子
            list.get(lastParentIndex).left = list.get(lastParentIndex * 2 + 1);
            // 右孩子,如果数组的长度为奇数才建立右孩子
            if (array.length % 2 == 1) {
                list.get(lastParentIndex).right = list.get(lastParentIndex * 2 + 2);
            }
        }

        return list.get(0);
    }
}

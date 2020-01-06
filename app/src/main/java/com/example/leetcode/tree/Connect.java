package com.example.leetcode.tree;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 */
public class Connect {
    /**
     * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
     *
     * struct Node {
     *   int val;
     *   Node *left;
     *   Node *right;
     *   Node *next;
     * }
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为
     * NULL。
     *
     * 初始状态下，所有 next 指针都被设置为 NULL。
     *
     * 提示：
     *
     * 你只能使用常量级额外空间。
     * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * 方法一：递归处理左右子树的连接，然后迭代将两个子树相连
     * 自己想的解法，原来他还有个名字叫"拉拉链解法"
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if(root == null || root.left == null) return root;
        Node leftTree = connect(root.left);
        Node rightTree = connect(root.right);
        while (leftTree != null) {
            leftTree.next = rightTree;
            leftTree = leftTree.right;
            rightTree = rightTree.left;
        }
        return root;
    }

    /**
     * 方法二：迭代
     * BFS的思路，记录每一行最左边的节点，然后用next来比拟队列的连接即可
     */
    public Node connect2(Node root) {
        //pre用来记录最左边的节点
        Node pre = root;
        while (pre != null) {
            //curr用来比拟队列指针
            Node curr = pre;
            while (curr != null) {
                if(curr.left != null) curr.left.next = curr.right;
                if(curr.right != null && curr.next != null) curr.right.next = curr.next.left;
                curr = curr.next;
            }
            pre = pre.left;
        }
        return root;
    }

}

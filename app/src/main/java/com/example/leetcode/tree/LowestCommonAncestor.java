package com.example.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  二叉树的最近公共祖先
 */
public class LowestCommonAncestor {
    /**
     * 二叉搜索树的最近公共祖先
     *
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

    /**
     * 二叉树的最近公共祖先
     *
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是
     * p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 示例 1:
     *
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输出: 3
     * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
     * 示例 2:
     *
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     * 输出: 5
     * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
     *  
     *
     * 说明:
     *
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉树中。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：递归
     */
    public TreeNode ans;
    public TreeNode lowestCommonAncestorTree(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;

        boolean leftNode = findAncestor(root.left, p, q);
        //最近公共祖先在左子树
        if(ans != null) return ans;
        //左子树中只找到一个给定节点，说明另一个一定在右子树，提前结束
        if(leftNode) return root;

        //最近的公共祖先在右子树
        findAncestor(root.right, p, q);
        return ans;
    }

    /**
     * 辅助方法，中序遍历，寻找该子树的公共祖先，若两节点都在该子树，更新ans，ans存储的即为最终结果
     * @param root
     * @param p
     * @param q
     * @return true 表示该子树只存在两节点中的一个节点
     */
    public boolean findAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return false;

        boolean leftNode = findAncestor(root.left, p, q);
        //最近公共祖先在左子树
        if(ans != null) return false;   //找到ans以后返回的boolean值已没有意义

        boolean mid = false;
        if(p == root || q == root) {
            mid = true;
            //两节点一个在左子树一个在root，找到最近的公共祖先
            if(leftNode) {
                ans = root;
                return false;
            }
        }

        boolean rightNode = findAncestor(root.right, p, q);
        //最近公共祖先在左子树
        if(ans != null) return false;

        //两节点一个在右子树一个在root，找到最近的公共祖先
        if(rightNode && (leftNode || mid)) {
            ans = root;
            return false;
        }

        //该子树只存在两节点中的一个节点或者一个也没有
        return (rightNode || leftNode || mid);
    }

    /**
     * 方法二：LeetCode题解，对上面方法的简化,但是
     *
     * 1.从根节点开始遍历树。
     * 2.如果当前节点本身是 p 或 q 中的一个，我们会将变量 mid 标记为 true，并继续搜索左右分支中的另一个节点。
     * 3.如果左分支或右分支中的任何一个返回 true，则表示在下面找到了两个节点中的一个。
     * 4.如果在遍历的任何点上，左、右或中三个标志中的任意两个变为 true，这意味着我们找到了节点 p 和 q 的最近公共祖先。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/er-cha-shu-de-zui-jin-gong-gong-zu-xian-by-leetcod/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public TreeNode res;
    public TreeNode lowestCommonAncestorTree2(TreeNode root, TreeNode p, TreeNode q) {
        recurseTree(root, p, q);
        return res;
    }

    private boolean recurseTree(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return false;
        int left = recurseTree(root.left, p, q) ? 1 : 0;

        //在这里我加了一个提前剪枝
        if(res != null) return true;

        int right = recurseTree(root.right, p, q) ? 1 : 0;
        int mid = (root == p || root == q) ? 1 : 0;
        //若p和q均在同一个子树，mid + left + right的和为1，不会更新res的
        if(mid + left + right >= 2) {
            res = root;
        }
        return (mid + left + right) > 0;
    }

    /**
     * 方法三：使用父指针的迭代.层次遍历
     *
     * 从根节点开始遍历树。
     * 在找到 p 和 q 之前，将父指针存储在字典中。
     * 一旦我们找到了 p 和 q，我们就可以使用父亲字典获得 p 的所有祖先，并添加到一个称为祖先的集合中。
     * 同样，我们遍历节点 q 的祖先。如果祖先存在于为 p 设置的祖先中，这意味着这是 p 和 q 之间的第一个共同祖先
     * （同时向上遍历），因此这是 LCA 节点。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/er-cha-shu-de-zui-jin-gong-gong-zu-xian-by-leetcod/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        //栈进行中序遍历
        Deque<TreeNode> stack = new ArrayDeque<>();

        //key为节点，value为它的直系父节点
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        parent.put(root, null);
        stack.push(root);

        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pop();

            if(node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if(node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        //存储p的所有祖先
        Set<TreeNode> ancestor = new HashSet<>();

        while (p != null) {
            ancestor.add(p);
            p = parent.get(p);
        }
        while (! ancestor.contains(q))
            q = parent.get(q);
        return q;
    }

    /**
     * 方法四：使用栈进行中序遍历，用LCA指针避免（找到p和q后）回溯。LCA指针始终指向当前节点和第一个找到
     * 的p或q的最低公共祖先。
     */
    public TreeNode lowestCommonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
        int index = 0;
        TreeNode ans = null;
        TreeNode current = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (current != null || !stack.isEmpty()) {
            if(current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.peek();
                if(stack.size() < index) {
                    index = stack.size();
                    ans = current;
                }
                if(current == q || current == p) {
                    if(ans == null) {
                        ans = current;
                        index = stack.size();
                    } else return ans;
                }
                stack.pop();
                current = current.right;
            }
        }
        return null;
    }


}

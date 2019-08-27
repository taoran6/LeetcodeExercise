package com.example.leetcode.tree;

/**
 * 路径总和 III
 */
public class PathSum {
    /**
     * 给定一个二叉树，它的每个结点都存放着一个整数值。
     *
     * 找出路径和等于给定数值的路径总数。
     *
     * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     *
     * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
     *
     * 示例：
     *
     * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     *
     *       10
     *      /  \
     *     5   -3
     *    / \    \
     *   3   2   11
     *  / \   \
     * 3  -2   1
     *
     * 返回 3。和等于 8 的路径有:
     *
     * 1.  5 -> 3
     * 2.  5 -> 2 -> 1
     * 3.  -3 -> 11
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/path-sum-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     * 方法一：两个递归
     * 路径的开头可以不是根节点，结束也可以不是叶子节点，是不是有点复杂？
     * 如果问题是这样：找出以根节点为开始，任意节点可作为结束，且路径上的节点和为 sum 的路径的个数；
     * 是不是前序遍历一遍二叉树就可以得到所有这样的路径？是的；
     * 如果这个问题解决了，那么原问题可以分解成多个这个问题；
     * 是不是和数线段是同一个问题，只不过线段变成了二叉树；
     * 在解决了以根节点开始的所有路径后，就要找以根节点的左孩子和右孩子开始的所有路径，三个节点构成了一个递归结构；
     * 递归真的好简单又好难；
     *
     * 作者：li-xin-lei
     * 链接：https://leetcode-cn.com/problems/path-sum-iii/solution/leetcode-437-path-sum-iii-by-li-xin-lei/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return rootPathSum(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int rootPathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        int ans = 0;
        if (root.val == sum) ans++;
        ans = ans + rootPathSum(root.left, sum - root.val) + rootPathSum(root.right, sum - root.val);

        return ans;
    }

    /**
     * 方法二：
     * todo 好累啊过一会儿再写吧 https://leetcode-cn.com/problems/path-sum-iii/solution/liang-chong-fang-fa-jian-dan-yi-dong-ban-ben-by-a3/
     * @param root
     * @param sum
     * @return
     */
    public int pathSum2(TreeNode root, int sum) {
        return 0;
    }
}

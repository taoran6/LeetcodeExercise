package com.example.leetcode.DP;

import com.example.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 96. 不同的二叉搜索树
 */
public class NumTrees {
    /**
     * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
     *
     * 示例:
     *
     * 输入: 3
     * 输出: 5
     * 解释:
     * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
     *
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 结题思路：假设n个节点存在二叉排序树的个数是G(n)，1为根节点，2为根节点，...，n为根节点，当1为根节点时，其
     * 左子树节点个数为0，右子树节点个数为n-1，同理当2为根节点时，其左子树节点个数为1，右子树节点为n-2，所以可
     * 得G(n) = G(0)*G(n-1)+G(1)*(n-2)+...+G(n-1)*G(0)
     *
     * 在上述方法中，由于根各自不同，每棵二叉树都保证是独特的
     */
    public int numTrees(int n) {
        if(n == 0) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j ++) {
                dp[i] += dp[j] * dp[i - j -1];
            }
        }

        return dp[n];
    }

    /**
     * 方法二：数学方法
     *
     * 事实上 G(n)函数的值被称为 卡塔兰数
     * n
     * ​
     *  。卡塔兰数更便于计算的定义如下:
     *
     * C(0) = 1, C(n) = C(n) * 2(2n+1)/(n+2)
     * ​
     *
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees/solution/bu-tong-de-er-cha-sou-suo-shu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int numTrees2(int n) {
        // Note: we should use long here instead of int, otherwise overflow
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }

    /**
     * 95. 不同的二叉搜索树 II
     *
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
     *
     * 示例:
     *
     * 输入: 3
     * 输出:
     * [
     *   [1,null,3,2],
     *   [3,2,null,1],
     *   [3,1,null,null,2],
     *   [2,1,3],
     *   [1,null,2,null,3]
     * ]
     * 解释:
     * 以上的输出对应以下 5 种不同结构的二叉搜索树：
     *
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 这道题也可以用动态规划来做，但因为要生成实际的树，优化效果不太明显。
     *
     * 方法：递归即可
     */
    public List<TreeNode> generateTrees(int n) {
        if(n < 1) return new LinkedList<>();
        return generateTreesHelp(1, n);
    }

    public List<TreeNode> generateTreesHelp(int start, int end) {
        List<TreeNode> ans = new LinkedList<>();
        if(start > end) {
            ans.add(null);
            return ans;
        }
        else {
            for (int i = start; i <= end; i++) {
                List<TreeNode> leftList = generateTreesHelp(start, i - 1);
                List<TreeNode> rightList = generateTreesHelp(i + 1, end);

                for (TreeNode leftNode : leftList){
                    for (TreeNode rightNode : rightList) {
                        TreeNode root = new TreeNode(i);
                        root.left = leftNode;
                        root.right = rightNode;
                        ans.add(root);
                    }
                }
            }
        }
        return ans;
    }
}

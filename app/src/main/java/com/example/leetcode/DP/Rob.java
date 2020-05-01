package com.example.leetcode.DP;

import com.example.leetcode.tree.TreeNode;

/**
 * 打家劫舍
 */
public class Rob {
    /**
     * 198. 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋
     * 装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     *
     * 示例 1:
     *
     * 输入: [1,2,3,1]
     * 输出: 4
     * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 2:
     *
     * 输入: [2,7,9,3,1]
     * 输出: 12
     * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int pk = 0;
        int pk1 = 0;
        int pk2 = 0;
        for (int i = 0; i < nums.length; i ++) {
            pk = Math.max(pk1, pk2 + nums[i]);
            pk2 = pk1;
            pk1 = pk;
        }

        return pk;
    }

    /**
     * 213. 打家劫舍 II
     *
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着
     * 第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
     * 被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     *
     * 示例 1:
     *
     * 输入: [2,3,2]
     * 输出: 3
     * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     * 示例 2:
     *
     * 输入: [1,2,3,1]
     * 输出: 4
     * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 思路：因为第0个和第n-1个不能一起抢，所以分开考虑只抢0和只抢n-1的情况
     */
    public int robII(int[] nums) {
        if(nums.length == 1) return nums[0];
        //先计算只抢[0, n-2]的前 n-1 个房屋
        int dp1 = 0;
        int dpPre1 = 0;
        int dpPre2 = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            dp1 = Math.max(dpPre1, dpPre2 + nums[i]);
            dpPre2 = dpPre1;
            dpPre1 = dp1;
        }

        //再计算只抢[1, n-1]的后 n-1 个房屋
        int dp2 = 0;
        dpPre1 = 0;
        dpPre2 = 0;
        for (int i = 1; i < nums.length; i++) {
            dp2 = Math.max(dpPre1, dpPre2 + nums[i]);
            dpPre2 = dpPre1;
            dpPre1 = dp2;
        }

        //这里dp1和dp2相等的有一种情况是第一个和最后一个都不抢是最优解
        return Math.max(dp1, dp2);
    }

    /**
     * 337. 打家劫舍 III
     *
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为
     * “根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有
     * 房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     *
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     *
     * 示例 1:
     *
     * 输入: [3,2,3,null,3,null,1]
     *
     *      3
     *     / \
     *    2   3
     *     \   \
     *      3   1
     *
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
     * 示例 2:
     *
     * 输入: [3,4,5,1,3,null,1]
     *
     *      3
     *     / \
     *    4   5
     *   / \   \
     *  1   3   1
     *
     * 输出: 9
     * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int robIII(TreeNode root) {
        return robIIIHelp(root)[0];
//        int[] res = robIIIHelp2(root);
//        return Math.max(res[0], res[1]);
    }

    private int[] robIIIHelp(TreeNode root) {
        //ret[0]为整棵树的最大值，ret[1]为左子树的最大值,ret[2]为右子树的最大值
        int[] ret = new int[3];
        if(root == null) return ret;
        int[] left = robIIIHelp(root.left);
        int[] right = robIIIHelp(root.right);
        ret[1] = left[0];
        ret[2] = right[0];
        //关键是这里，用到了儿子节点及孙子节点的最大值
        ret[0] = Math.max(root.val + left[1] + left[2] + right[1] + right[2], left[0] + right[0]);
        return ret;
    }

    /**
     * 优化，这里用一个int[2]就可以了，int[0] 是不偷当前节点，int[1]是偷当前节点，节省了空间
     */
    private int[] robIIIHelp2(TreeNode root) {
        int[] ret = new int[2];
        if(root == null) return ret;
        int[] left = robIIIHelp2(root.left);
        int[] right = robIIIHelp2(root.right);
        // 不抢，下家可抢可不抢，取决于收益大小
        ret[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 抢，下家就不能抢了
        ret[1] = left[0] + right[0] + root.val ;
        return ret;
    }
}

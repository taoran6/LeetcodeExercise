package com.example.leetcode.tree;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public int pathSum0(TreeNode root, int sum) {
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

    private int ans = 0;

    /**
     * 方法二：
     * 思想是遍历到某个节点时，检查该节点到根节点的路径上包含该节点的所有可能，如此每一个节点在检查可能的路径时都
     * 不会重复。
     *
     * 比如假设有路径1,2,3,4，-4,5，sum=5，如果遍历到3时，只需要检查1,2,3中是否有包含3的解，发现2,3是一个解；
     *
     * 遍历到5时只需检查包含5的解，包括5，以及4，-4,5两个解。这样就没有重复检查。因为包含5的解在之前一定没被检查过。
     *
     * 作者：nannan
     * 链接：https://leetcode-cn.com/problems/path-sum-iii/solution/da-jia-du-shuang-zhong-di-gui-wo-zhi-yong-yi-ge-di/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @param sum
     * @return
     */
    public int pathSum1(TreeNode root, int sum) {
        List<Integer> list = new ArrayList<>();
        findPath(root, sum, list);
        return ans;
    }

    private void findPath(TreeNode root, int sum, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        int add = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            add += list.get(i);
            if (add == sum) ans++;
        }
        findPath(root.left, sum, list);
        findPath(root.right, sum, list);
        list.remove(list.size() - 1);
    }

    /**
     * 方法三：所以第二种做法，采取了类似于数组的前n项和的思路，比如sum[4] == sum[1]，那么1到4之间的和肯定为0
     *
     * 对于树的话，采取DFS加回溯，每次访问到一个节点，把该节点加入到当前的pathSum中
     * 然后判断是否存在一个之前的前n项和，其值等于pathSum与sum之差
     * 如果有，就说明现在的前n项和，减去之前的前n项和，等于sum，那么也就是说，这两个点之间的路径和，就是sum
     *
     * 最后要注意的是，记得回溯，把路径和弹出去
     *
     * 作者：a380922457
     * 链接：https://leetcode-cn.com/problems/path-sum-iii/solution/liang-chong-fang-fa-jian-dan-yi-dong-ban-ben-by-a3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * TODO 其实还是没看懂,感觉题解没说清楚？前n项和？
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        return helper(root, map, sum, 0);
    }

    @SuppressLint("NewApi")
    int helper(TreeNode root, HashMap<Integer, Integer> map, int sum, int pathSum){
        int res = 0;
        if(root == null) return 0;

        pathSum += root.val;
        res += map.getOrDefault(pathSum - sum, 0);
        map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);
        res = helper(root.left, map, sum, pathSum) + helper(root.right, map, sum, pathSum) + res;
        map.put(pathSum, map.get(pathSum) - 1);
        return res;
    }

}

package com.example.leetcode.backtracking;

import java.util.Arrays;

/**
 * 安卓系统手势解锁
 */
public class NumberOfPatterns {
    /**
     * 351. 安卓系统手势解锁
     *
     * 我们都知道安卓有个手势解锁的界面，是一个 3 x 3 的点所绘制出来的网格。
     *
     * 给你两个整数，分别为 ​​m 和 n，其中 1 ≤ m ≤ n ≤ 9，那么请你统计一下有多少种解锁手势，是至少需要经过 m 个
     * 点，但是最多经过不超过 n 个点的。
     *
     *  
     *
     * 先来了解下什么是一个有效的安卓解锁手势:
     *
     * 每一个解锁手势必须至少经过 m 个点、最多经过 n 个点。
     * 解锁手势里不能设置经过重复的点。
     * 假如手势中有两个点是顺序经过的，那么这两个点的手势轨迹之间是绝对不能跨过任何未被经过的点。
     * 经过点的顺序不同则表示为不同的解锁手势。
     *
     * 解释:
     *
     * | 1 | 2 | 3 |
     * | 4 | 5 | 6 |
     * | 7 | 8 | 9 |
     * 无效手势：4 - 1 - 3 - 6
     * 连接点 1 和点 3 时经过了未被连接过的 2 号点。
     *
     * 无效手势：4 - 1 - 9 - 2
     * 连接点 1 和点 9 时经过了未被连接过的 5 号点。
     *
     * 有效手势：2 - 4 - 1 - 3 - 6
     * 连接点 1 和点 3 是有效的，因为虽然它经过了点 2 ，但是点 2 在该手势中之前已经被连过了。
     *
     * 有效手势：6 - 5 - 4 - 1 - 9 - 2
     * 连接点 1 和点 9 是有效的，因为虽然它经过了按键 5 ，但是点 5 在该手势中之前已经被连过了。
     *
     *  
     *
     * 示例:
     *
     * 输入: m = 1，n = 1
     * 输出: 9
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/android-unlock-patterns
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 注：利用对称的性质可以优化算法，我们发现从数字 1, 3, 7, 9 出发的手势是相同的，同理从 2, 4, 6, 8 出发的
     * 也是。因此对这些相同的组我们只需要对结果乘以 4 即可。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/android-unlock-patterns/solution/an-zhuo-xi-tong-shou-shi-jie-suo-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    int count = 0;
    int max;
    int min;

    public int numberOfPatterns(int m, int n) {
        boolean[] isVisited = new boolean[9];
        min = m; max = n;
        int ans = 0;

        //利用对称性，先计算点1为起始点
        isVisited[0] = true;
        backTracking(isVisited, 1, 0);

        //计算点2为起始点
        Arrays.fill(isVisited, false);
        isVisited[1] = true;
        backTracking(isVisited, 1, 1);
        //两种情况的和乘以4
        ans += count * 4;

        //单独计算点4为起始点
        Arrays.fill(isVisited, false);
        isVisited[4] = true;
        count = 0;
        backTracking(isVisited, 1, 4);
        //结果相加
        ans += count;


        return ans;
    }

    /**
     * 回溯
     * @param isVisited
     * @param step  至今已经走过多少个点
     * @param lastDot   上一个走过的点
     */
    private void backTracking(boolean[] isVisited, int step, int lastDot) {
        if(step >= min) count++;        //有效手势
        if(step == max) return ;        //达到最多的点，提前返回

        for(int i = 0; i < 9; i++) {
            if(isValid(isVisited, i, lastDot)) {
                isVisited[i] = true;
                backTracking(isVisited, step + 1, i);
                isVisited[i] = false;
            }
        }
    }

    /**
     * 判断经过的点是否有效，这里其实很trick，看的官方题解才知道
     * 
     * 我们需要记录上一个访问的数字 last。算法需要检查是否满足以下任一条件：
     * 1. 从 last 到 i 之间是国际象棋中马的移动，或者 last 和 i 是同一行或列的相邻元素。这种情况下，
     * 两个数字之和应当为奇数。
     * 
     * 2. 连接 last 和 i 的中间元素 mid 已经被访问过，比方说 last 和 i 选择的是对角线上的两点，
     * 那么中间点 mid = 5 应当已经选过。
     * 
     * 3. last 和 i 是对角线上的相邻元素。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/android-unlock-patterns/solution/an-zhuo-xi-tong-shou-shi-jie-suo-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param isVisited
     * @param i 本次判断的点
     * @param lastDot   上次经过的点
     * @return
     */
    private boolean isValid(boolean[] isVisited, int i, int lastDot) {
        if(isVisited[i]) return false;

        //和为奇数
        if((i + lastDot) % 2 == 1) return true;

        int mid = (i + lastDot) / 2;
        // 两个点是对角线上的点
        if(mid == 4) return isVisited[mid];

        //两个点是是对角线上的相邻元素
        if((i % 3) != (lastDot % 3) && (i / 3 != lastDot / 3)) return true;
        //不是相邻元素
        return isVisited[mid];
    }
}

package com.example.leetcode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并区间
 */
public class Merge {
    /**
     * 给出一个区间的集合，请合并所有重叠的区间。
     *
     * 示例 1:
     *
     * 输入: [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2:
     *
     * 输入: [[1,4],[4,5]]
     * 输出: [[1,5]]
     * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-intervals
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0) return intervals;

        //实验表明，使用lambda表达式的运行结果为46ms而，直接按照下面传统方法写时间为7ms
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> ans = new ArrayList<>();

        int[] array = new int[]{intervals[0][0] , intervals[0][1]};
        for (int i = 1; i < intervals.length; i++) {
            if (array[1] < intervals[i][0]) {   //区间不相连
                ans.add(new int[]{array[0], array[1]});
                array[0] = intervals[i][0];
                array[1] = intervals[i][1];
            } else {
                array[1] = Math.max(array[1] , intervals[i][1]);
            }
        }
        ans.add(array);

        //下面的代码可替换为 return ans.toArray(new int[0][2]);
        int[][] ansArray = new int[ans.size()][2];
        for(int i = 0; i < ans.size(); i++) {
            ansArray[i][0] = ans.get(i)[0];
            ansArray[i][1] = ans.get(i)[1];
        }
        return ansArray;
    }
}

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

    /**
     * 986. 区间列表的交集
     *
     * 给定两个由一些 闭区间 组成的列表，每个区间列表都是成对不相交的，并且已经排序。
     *
     * 返回这两个区间列表的交集。
     *
     * （形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b。两个闭区间的交集是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3]。）
     *
     *
     * 输入：A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
     * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     *  
     *
     * 提示：
     *
     * 0 <= A.length < 1000
     * 0 <= B.length < 1000
     * 0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/interval-list-intersections
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 核心是双指针
     */
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int indexA = 0;
        int indexB = 0;
        List<int[]> ans = new ArrayList<>();


        while (indexA < A.length && indexB < B.length) {
            int[] intervalA = A[indexA];
            int[] intervalB = B[indexB];


            //两个区间没有交集
            if(intervalA[1] < intervalB[0]) {
                indexA ++;
            } else if(intervalB[1] < intervalA[0]) {
                indexB ++;
            }

            //有交集
            else {
                int[] section = new int[2];
                section[0] = Math.max(intervalA[0], intervalB[0]);
                section[1] = Math.min(intervalA[1], intervalB[1]);
                ans.add(section);
                if(intervalA[1] < intervalB[1]) {
                    indexA ++;
                } else {
                    indexB ++;
                }
            }
        }

        return ans.toArray(new int[ans.size()][2]);
    }
}

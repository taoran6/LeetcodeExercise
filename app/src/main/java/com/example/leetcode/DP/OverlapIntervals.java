package com.example.leetcode.DP;

import java.util.Arrays;
import java.util.Comparator;

public class OverlapIntervals {
    /**
     * 435. 无重叠区间
     *
     * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     *
     * 注意:
     *
     * 可以认为区间的终点总是大于它的起点。
     * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
     * 示例 1:
     *
     * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
     *
     * 输出: 1
     *
     * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
     * 示例 2:
     *
     * 输入: [ [1,2], [1,2], [1,2] ]
     *
     * 输出: 2
     *
     * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
     * 示例 3:
     *
     * 输入: [ [1,2], [2,3] ]
     *
     * 输出: 0
     *
     * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/non-overlapping-intervals
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 核心：贪心算法
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;

        // 首先按区间的 end 排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        //count记录不重叠的区间，最少有一个区间不重叠
        int count = 1;
        int end = intervals[0][1];  //从end最小的那个区间开始,即结束最早的那个区间
        for (int i = 0; i < intervals.length; i++) {
            if(end <= intervals[i][0]) {
                //找到下一个选择的区间了
                count ++;
                end = intervals[i][1];
            }
        }

        return intervals.length - count;
    }

    /**
     * 452. 用最少数量的箭引爆气球
     *
     * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水
     * 平的，所以y坐标并不重要，因此只要知道开始和结束的x坐标就足够了。开始坐标总是小于结束坐标。平面内最多存在
     * 10^4个气球。
     *
     * 一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为
     * xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被
     * 射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
     *
     * Example:
     *
     * 输入:
     * [[10,16], [2,8], [1,6], [7,12]]
     *
     * 输出:
     * 2
     *
     * 解释:
     * 对于该样例，我们可以在x = 6（射爆[2,8],[1,6]两个气球）和 x = 11（射爆另外两个气球）。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 两题解法相同
     */
    public int findMinArrowShots(int[][] points) {
        if(points == null || points.length == 0) return 0;

        // 首先按区间的 end 排序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        //count记录不重叠的区间，最少有一个区间不重叠
        int count = 1;
        int end = points[0][1];  //从end最小的那个区间开始,即结束最早的那个区间
        for (int i = 0; i < points.length; i++) {
            if(end < points[i][0]) {    //这里不同的地方是等于也算重叠区间，因为xstart ≤ x ≤ xend气球会被引爆
                //找到下一个选择的区间了
                count ++;
                end = points[i][1];
            }
        }

        return count;
    }

    /**
     * 253. Meeting Rooms II
     *
     * Given an array of meeting time intervals consisting of start and end times 
     * [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
     *
     * For example,
     * Given [[0, 30],[5, 10],[15, 20]],
     * return 2.
     *
     * 即需要求解同一时刻最多有几个会议在同时召开
     *
     * 方法一：对end排序
     */
    private int meetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        //记录同时召开的最大会议数
        int maxMeetings = 1;
        //记录当前同时召开的会议数
        int currentCount = 1;

        int firstEndIndex = 0;
        for (int i = 1; i < intervals.length; i++) {
            currentCount ++;
            //这里看似两重嵌套但是实际上总共只有两次对数组的遍历
            while (firstEndIndex < i && intervals[i][0] >= intervals[firstEndIndex][1]) {
                firstEndIndex ++;
                currentCount --;
            }
            maxMeetings = Math.max(maxMeetings, currentCount);
        }

        return maxMeetings;
    }

    /**
     * 方法二：对start和end都排序
     *
     * 很好理解，有新的会议开始就要多增加一个会议室，有会议结束了就减少一个会议室，不用担心整个会议持续时长
     */
    private int meetingRooms2(int[][] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];

        Arrays.sort(start);
        Arrays.sort(end);

        int room = 0;
        int endIndex = 0;
        for (int i = 0; i < start.length; i++) {
            if (start[i] < end[endIndex]) {
                //都在开会，必须增加新的会议室
                room ++;
            } else {
                //有会议室空出来了
                endIndex ++;
            }
        }
        //这里room就是开辟的最大空间了
        return room;
    }

}

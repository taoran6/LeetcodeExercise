package com.example.leetcode.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 406. 根据身高重建队列
 */
public class ReconstructQueue {
    /**
     * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面
     * 且身高大于或等于h的人数。 编写一个算法来重建这个队列。
     *
     * 注意：
     * 总人数少于1100人。
     *
     * 示例
     *
     * 输入:
     * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
     *
     * 输出:
     * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[][] reconstructQueue(int[][] people) {
        List<int[]> ans = new ArrayList<>();
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });

        for (int i = 0; i < people.length; i++) {
            ans.add(people[i][1], people[i]);
        }

        //List转数组的方法
        return ans.toArray(new int[ans.size()][]);
    }
}

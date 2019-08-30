package com.example.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角
 */
public class GenerateTriangle {
    /**
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     * 示例:
     *
     * 输入: 5
     * 输出:
     * [
     *      [1],
     *     [1,1],
     *    [1,2,1],
     *   [1,3,3,1],
     *  [1,4,6,4,1]
     * ]
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if(numRows <= 0) return ans;
        List<Integer> row1 = new ArrayList<>();
        row1.add(1);
        ans.add(row1);
        for(int i = 1; i < numRows; i++) {
            List<Integer> list = new ArrayList<>(row1);
            for(int j = 1; j < i; j++) {
                list.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
            }
            list.add(1);
            ans.add(list);
        }
        return ans;
    }
}

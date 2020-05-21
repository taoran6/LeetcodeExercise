package com.example.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * N皇后问题
 */
public class SolveNQueens {
    /**
     * 51. N皇后
     *
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
     *
     * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     *
     * 示例:
     *
     * 输入: 4
     * 输出: [
     *  [".Q..",  // 解法 1
     *   "...Q",
     *   "Q...",
     *   "..Q."],
     *
     *  ["..Q.",  // 解法 2
     *   "Q...",
     *   "...Q",
     *   ".Q.."]
     * ]
     * 解释: 4 皇后问题存在两个不同的解法。
     *
     * PS: 皇后可以攻击同一行、同一列、左上左下右上右下四个方向的任意单位。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/n-queens
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        //初始化棋盘为'.'
        char[][] track = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                track[i][j] = '.';
            }
        }
        //回溯
        backtrace(ans, track, 0, n);
        return ans;
    }

    /**
     * 回溯
     *
     * // 路径:track 中小于 row 的那些行都已经成功放置了皇后
     * // 选择列表:第 row 行的所有列都是放置皇后的选择
     * // 结束条件:row 超过 board 的最后一行
     *
     * @param ans
     * @param track
     * @param row
     * @param n
     */
    private void backtrace(List<List<String>> ans, char[][] track, int row, int n) {
        // 触发结束条件
        if(row == n) {
            ans.add(arrayToList(track, n));
            return;
        }

        for(int i = 0; i < n; i++) {
            if(isValid(track, row, i)) {    // 排除不合法选择
                // 做选择
                track[row][i] = 'Q';
                // 进入下一行决策
                backtrace(ans, track, row + 1, n);
                // 撤销选择
                track[row][i] = '.';
            }
        }
    }

    /**
     * 判断track[row][col]的位置可不可以放置一个皇后，
     * 只用判断所在行小于row的位置即可
     *
     * @param track
     * @param row
     * @param col
     * @return
     */
    private boolean isValid(char[][] track, int row, int col) {
        //同列是否有皇后
        for(int i = 0; i < row; i++) {
            if(track[i][col] == 'Q') return false;
        }
        //左上角有没有
        for(int i = 1; i <= track.length; i++) {
            if(row - i < 0 || col - i < 0) break;
            if(track[row-i][col-i] == 'Q') return false;
        }
        //右上角有没有
        for (int i = 1; i <= track.length; i++) {
            if(row - i < 0 || col + i >= track.length) break;
            if(track[row-i][col+i] == 'Q') return false;
        }
        return true;
    }

    /**
     * char[][] 转List<String>
     */
    private List<String> arrayToList(char[][] board, int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ans.add(String.valueOf(board[i]));
        }
        return ans;
    }
}

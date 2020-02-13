package com.example.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 单词搜索
 */
public class ExistWord {
    /**
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     *
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一
     * 个单元格内的字母不允许被重复使用。
     *
     * 示例:
     *
     * board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     *
     * 给定 word = "ABCCED", 返回 true.
     * 给定 word = "SEE", 返回 true.
     * 给定 word = "ABCB", 返回 false.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/word-search
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0 || board[0].length == 0 || word.length() == 0)
            return false;

        int m = board.length;       // 行数
        int n = board[0].length;    // 列数

        char[] words = word.toCharArray();
        for (int i = 0; i< m; i++) {
            for (int j = 0; j < n; j++) {
                if(words[0] == board[i][j]) {
                    if(words.length == 1) return true;
                    //可以采用这种方式标记已经访问过，减少一个 m*n 的boolean数组空间
                    board[i][j] = '.';
                    if(exist(board, words, 1, i, j)) return true;
                    board[i][j] = words[0];
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, char[] words, int index, int x, int y) {
        int[][] newPoint = new int[][] {{x - 1, y}, {x + 1, y}, {x, y -1}, {x, y + 1}};
        for (int i = 0; i < 4; i++) {
            int newX = newPoint[i][0];
            int newY = newPoint[i][1];
            if (newX >= 0 && newY >= 0 && newX < board.length && newY < board[0].length
                    && words[index] == board[newX][newY]) {
                if(index == words.length - 1) return true;
                board[newX][newY] = '.';
                if(exist(board, words, index + 1, newX, newY)) return true;
                board[newX][newY] = words[index];
            }
        }
        return false;
    }

    /**
     * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子
     * 开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径
     * 不能再进入该格.
     *
     * @param matrix
     * @param rows
     * @param cols
     * @param str
     * @return
     */
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if(rows == 0 || cols == 0 || matrix.length == 0 || str.length == 0) return false;

        char[][] board = new char[rows][cols];
        boolean[][] isVisited = new boolean[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++) {
                board[i][j] = matrix[i * cols + j];
            }
        }

        for (int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++) {
                if(str[0] == board[i][j]) {
                    if(str.length == 1) return true;
                    isVisited[i][j] = true;
                    if(existPath(board, str, 1, i, j, isVisited)) return true;
                    isVisited[i][j] = false;
                }
            }
        }
        return false;

    }

    private boolean existPath(char[][] board, char[] words, int index, int x, int y,
                              boolean[][] isVisited) {
        int[][] newPoint = new int[][] {{x - 1, y}, {x + 1, y}, {x, y -1}, {x, y + 1}};
        for (int i = 0; i < 4; i++) {
            int newX = newPoint[i][0];
            int newY = newPoint[i][1];
            if (newX >= 0 && newY >= 0 && newX < board.length && newY < board[0].length
                    && words[index] == board[newX][newY] && !isVisited[newX][newY]) {
                if(index == words.length - 1) return true;
                isVisited[newX][newY] = true;
                if(existPath(board, words, index + 1, newX, newY, isVisited)) return true;
                isVisited[newX][newY] = false;
            }
        }
        return false;
    }
}

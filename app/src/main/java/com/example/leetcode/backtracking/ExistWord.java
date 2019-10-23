package com.example.leetcode.backtracking;

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
        if(board == null || board.length == 0 || board[0].length == 0 || word.length() == 0) return false;

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
}

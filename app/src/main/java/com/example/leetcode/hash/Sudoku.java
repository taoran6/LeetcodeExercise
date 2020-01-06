package com.example.leetcode.hash;

import java.util.HashSet;

/**
 * 数独
 */
public class Sudoku {
    /**
     * 36. 有效的数独
     * <p>
     * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
     * <p>
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     * <p>
     * 数独部分空格内已填入了数字，空白格用 '.' 表示。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * [
     * ["5","3",".",".","7",".",".",".","."],
     * ["6",".",".","1","9","5",".",".","."],
     * [".","9","8",".",".",".",".","6","."],
     * ["8",".",".",".","6",".",".",".","3"],
     * ["4",".",".","8",".","3",".",".","1"],
     * ["7",".",".",".","2",".",".",".","6"],
     * [".","6",".",".",".",".","2","8","."],
     * [".",".",".","4","1","9",".",".","5"],
     * [".",".",".",".","8",".",".","7","9"]
     * ]
     * 输出: true
     * 示例 2:
     * <p>
     * 输入:
     * [
     *   ["8","3",".",".","7",".",".",".","."],
     *   ["6",".",".","1","9","5",".",".","."],
     *   [".","9","8",".",".",".",".","6","."],
     *   ["8",".",".",".","6",".",".",".","3"],
     *   ["4",".",".","8",".","3",".",".","1"],
     *   ["7",".",".",".","2",".",".",".","6"],
     *   [".","6",".",".",".",".","2","8","."],
     *   [".",".",".","4","1","9",".",".","5"],
     *   [".",".",".",".","8",".",".","7","9"]
     * ]
     * 输出: false
     * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
     * 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
     * 说明:
     * <p>
     * 一个有效的数独（部分已被填充）不一定是可解的。
     * 只需要根据以上规则，验证已经填入的数字是否有效即可。
     * 给定数独序列只包含数字 1-9 和字符 '.' 。
     * 给定数独永远是 9x9 形式的。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-sudoku
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 方法一：使用HashSet，迭代3次
     */
    public boolean isValidSudoku(char[][] board) {
        HashSet<Character> characterHashSet = new HashSet<>();
        //每一行
        for (int i = 0; i < 9; i++) {
            characterHashSet.clear();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && !characterHashSet.add(board[i][j])) return false;
            }
        }

        //每一列
        for (int i = 0; i < 9; i++) {
            characterHashSet.clear();
            for (int j = 0; j < 9; j++) {
                if (board[j][i] != '.' && !characterHashSet.add(board[j][i])) return false;
            }
        }

        //每3 * 3格
        int[] offset = new int[]{0, 3, 6};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                characterHashSet.clear();
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        if (board[x + offset[i]][y + offset[j]] != '.' &&
                                !characterHashSet.add(board[x + offset[i]][y + offset[j]]))
                            return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 方法二：只遍历一次，使用3个HashSet数组。为了节省内存，这里HashSet可以用数组代替
     */
    public boolean isValidSudoku2(char[][] board) {
        int[][] row = new int[9][9];
        int[][] col = new int[9][9];
        int[][] box = new int[9][9];
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++) {
                if(board[i][j] == '.') continue;
                if(row[i][board[i][j] - '1']++ > 0) return false;
                if(col[j][board[i][j] - '1']++ > 0) return false;
                //注意这里box_index是怎么计算的
                int box_index = (i / 3) * 3 + j / 3;
                if(box[box_index][board[i][j] - '1']++ > 0) return false;
            }
        }
        return true;
    }

}

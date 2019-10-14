package com.example.leetcode.array;

/**
 * 旋转数组
 */
public class Rotate {
    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     *
     * 示例 1:
     *
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     *
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     * 说明:
     *
     * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
     * 要求使用空间复杂度为 O(1) 的 原地 算法。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/rotate-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：这是我自己写的环状替换
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k % nums.length == 0) return;
        int step = 0;
        int start = 0;
        int size = nums.length;
        int tmp = nums[0];
        int index = 0;
        while (step < size) {
            if((index + k) % size == start) {
                nums[start] = tmp;
                start ++;
                index = start;
                tmp = nums[index];
                step ++;
                continue;
            }
            int next = (index + k) % size;
            int change = nums [next];
            nums[next] = tmp;
            tmp = change;
            index = next;
            step ++;
        }
    }

    /**
     * 方法二：简洁代码后的环状替换,注意do-while循环
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        k = k % nums.length;
        int step = 0;
        for(int start = 0; step < nums.length; start ++) {
            int index = start;
            int pre = nums[start];
            do {
                int next = (index + k) % nums.length;
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                index = next;
                step ++;
            } while (start != index);
        }
    }

    /**
     * 方法三：反转数组
     * @param nums
     * @param k
     */
    public void rotate3(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        int x = 1;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

    /**
     * 旋转图像
     *
     * 给定一个 n × n 的二维矩阵表示一个图像。
     *
     * 将图像顺时针旋转 90 度。
     *
     * 说明：
     *
     * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
     *
     * 示例 1:
     *
     * 给定 matrix =
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     * 示例 2:
     *
     * 给定 matrix =
     * [
     *   [ 5, 1, 9,11],
     *   [ 2, 4, 8,10],
     *   [13, 3, 6, 7],
     *   [15,14,12,16]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [15,13, 2, 5],
     *   [14, 3, 4, 1],
     *   [12, 6, 8, 9],
     *   [16, 7,10,11]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/rotate-image
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public void rotate(int[][] matrix) {
        if(matrix == null) return;
        int n = matrix.length;

        for(int i = 0 ; i < n / 2; i++) {   //从外层到内层
            for (int j = 0; j < n - 2 * i - 1; j++) {   //从左向右
                //旋转四个数，就地循环
                int tmp = matrix[i][i + j];
                matrix[i][i + j] = matrix[n - 1 - i - j][i];
                matrix[n - 1 - i - j][i] = matrix[n - 1 - i][n - 1 - i - j];
                matrix[n - 1 - i][n - 1 - i - j] = matrix[i + j][n  - 1 - i];
                matrix[i + j][n  - 1 - i] = tmp;
            }
        }
    }
}

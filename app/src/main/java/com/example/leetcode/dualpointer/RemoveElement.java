package com.example.leetcode.dualpointer;

/**
 * 移除元素
 */
public class RemoveElement {
    /**
     * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     *
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * 示例 1:
     *
     * 给定 nums = [3,2,2,3], val = 3,
     *
     * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-element
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;

        int i = 0;
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] != val) {
                nums[i] = nums[k];
                i++;
            }
        }

        return i;
    }

    /**
     * 解法二：运用"元素的顺序可以改变"
     *    考虑数组包含很少的要删除的元素的情况。例如，num=[1，2，3，5，4]，Val=4num=[1，2，3，5，4]，Val=4。
     * 之前的算法会对前四个元素做不必要的复制操作。
     *   当我们遇到 nums[i] = valnums[i]=val 时，我们可以将当前元素与最后一个元素进行交换，并释放最后一个元素。
     * 这实际上使数组的大小减少了 1。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/remove-element/solution/yi-chu-yuan-su-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param val
     * @return
     */
    public int removeElement2(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;

        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] != val) {
                i++;
            } else {
                nums[i] = nums[n - 1];      //下一次迭代时，nums[i]仍然被检查
                n--;
            }
        }
        return n;
    }

    /**
     * 移动零
     *
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 示例:
     *
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     *
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/move-zeroes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        if(nums == null) return;

        int i = 0;
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] != 0) {
                nums[i] = nums[k];
                i++;
            }
        }

        while (i < nums.length) nums[i ++] = 0;

    }

}

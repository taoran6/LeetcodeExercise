package com.example.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 找到所有数组中消失的数字
 */
public class FindDisappearedNumbers {
    /**
     * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
     *
     * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
     *
     * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
     *
     * 示例:
     *
     * 输入:
     * [4,3,2,7,8,2,3,1]
     *
     * 输出:
     * [5,6]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     * 将数组元素对应为索引的位置加n
     * 遍历加n后的数组，若数组元素值小于等于n，则说明数组下标值不存在，即消失的数字
     *
     * 题解：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/solution/cyuan-shu-zu-cao-zuo-by-haydenmiao/
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) return ans;
        for(int i = 0; i < nums.length; i++) {
            int index = (nums[i] - 1) % nums.length;
            nums[index] += nums.length;
        }

        for(int i = 0; i <= nums.length; i ++) {
            if(nums[i] < nums.length) ans.add(i+1);
        }
        return ans;
    }

    /**
     * 缺失数字
     *
     * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
     *
     * 示例 1:
     *
     * 输入: [3,0,1]
     * 输出: 2
     * 示例 2:
     *
     * 输入: [9,6,4,2,3,5,7,0,1]
     * 输出: 8
     * 说明:
     * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/missing-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：排序 略
     * 方法二：hash表 略
     *
     * 方法三：位运算
     * 我们知道数组中有 n 个数，并且缺失的数在 [0..n] 中。因此我们可以先得到 [0..n] 的异或值，
     * 再将结果对数组中的每一个数进行一次异或运算。未缺失的数在 [0..n] 和数组中各出现一次，因此异或后得
     * 到 0。而缺失的数字只在 [0..n] 中出现了一次，在数组中没有出现，因此最终的异或结果即为这个缺失的数字。
     *
     * 在编写代码时，由于 [0..n] 恰好是这个数组的下标加上 n，因此可以用 一次循环 完成所有的异或运算
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/missing-number/solution/que-shi-shu-zi-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int ans = 0;
        // 先和新补的索引异或一下
        ans ^= n;
        // 和其他的元素、索引做异或
        for (int i = 0; i < n; i++) {
            ans = ans ^ i ^ nums[i];
        }
        return ans;
    }

    /**
     * 方法四：高斯求和,不过可能会有int溢出情况
     * @param nums
     * @return
     */
    public int missingNumber2(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        for(int i = 0; i < n; i++) {
            sum -= nums[i];
        }
        return sum;
    }

    /**
     * 方法五：解决高斯求和的溢出问题，改用加减法
     */
    public int missingNumber3(int[] nums) {
        int n = nums.length;

        int ans = n;
        for (int i = 0; i < nums.length; i++) {
            ans = ans + i - nums[i];
        }
        return ans;
    }

    /**
     * 41. 缺失的第一个正数
     *
     * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
     *
     * 示例 1:
     *
     * 输入: [1,2,0]
     * 输出: 3
     * 示例 2:
     *
     * 输入: [3,4,-1,1]
     * 输出: 2
     * 示例 3:
     *
     * 输入: [7,8,9,11,12]
     * 输出: 1
     * 说明:
     *
     * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/first-missing-positive
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 首先我们可以不考虑负数和零，因为不需要考虑。同样可以不考虑大于 n 的数字，
     * 因为首次缺失的正数一定小于或等于 n + 1 。n为数组长度
     * 第一步：将数据放到他本来应该在的位置，即nums[i]=i+1,
     * 第二步：遍历数组，如果哪个位置nums[i]!=i+1,则他就是缺失的数。
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            //注意这里要忽略重复数字的出现，所以要判断 nums[nums[i] - 1] != nums[i]
            while (/*nums[i] != i + 1 &&*/ nums[i] >0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                //交换nums[i]和nums[nums[i] - 1]
                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }

        int i = 0;
        for (; i < n; i++) {
            if(nums[i] != i + 1) return i + 1;
        }
        return i + 1;
    }

}

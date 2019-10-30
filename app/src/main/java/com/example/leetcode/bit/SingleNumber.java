package com.example.leetcode.bit;

import java.util.HashSet;
import java.util.Set;

/**
 * 只出现一次的数字
 */
public class SingleNumber {
    /**
     * 只出现一次的数字 I
     *
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * 说明：
     *
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     *
     * 输入: [2,2,1]
     * 输出: 1
     * 示例 2:
     *
     * 输入: [4,1,2,1,2]
     * 输出: 4
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/single-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int singleNumberI(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }

        for (int i : set) {
            return i;
        }
        return 0;
    }

    /**
     * 解法二：使用位运算，异或
     * a ^ 0 = a
     * a ^ a = 0
     * a ^ b ^ a = （a ^ a）^ b = b
     */

    public int singleNumberI2(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans = ans ^ num;
        }
        return ans;
    }

    /**
     * 只出现一次的数字 II
     *
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
     *
     * 说明：
     *
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     *
     * 输入: [2,2,3,2]
     * 输出: 3
     * 示例 2:
     *
     * 输入: [0,1,0,1,0,1,99]
     * 输出: 99
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/single-number-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int singleNumberII(int[] nums) {
        //状态转移 00 -> 01 -> 10 -> 00 低位表示one，高位表示two
        int one = 0;
        int two = 0;
        for(int i = 0; i < nums.length; i++) {
            //其实就是逻辑电路，可以用数字电路里的卡洛图来求
            int newOne = (one & ~nums[i]) | (~one & nums[i] & ~two);
            two = (two & ~nums[i]) | (~two & one & nums[i]);
            one = newOne;
        }
        return one;
    }

    /**
     * 260.只出现一次的数字 III
     *
     * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
     *
     * 示例 :
     *
     * 输入: [1,2,1,3,2,5]
     * 输出: [3,5]
     * 注意：
     *
     * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
     * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/single-number-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 思路, 先全部异或一次, 得到的结果, 考察其的某个非0位(比如最高非0位), 那么只出现一次的两个数中, 在这个位上
     * 一个为0, 一个为1, 由此可以将数组中的元素分成两部分,重新遍历, 求两个异或值
     */
    public int[] singleNumberIII(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        //由于这两个只出现一次的元素不相等，则xor必不等于0，找到那位数为1的位置
        int i = 1;
        while ((xor & i) == 0) i = i << 1;

        int xor0 = 0;
        int xor1 = 0;
        for (int num : nums) {
            //根据这个i分成两组
            if((num & i) == 0) xor0 ^= num;
            else xor1 ^= num;
        }

        return new int[]{xor0, xor1};
    }

    /**
     * 找不同
     *
     * 给定两个字符串 s 和 t，它们只包含小写字母。
     *
     * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
     *
     * 请找出在 t 中被添加的字母。
     *
     *  
     *
     * 示例:
     *
     * 输入：
     * s = "abcd"
     * t = "abcde"
     *
     * 输出：
     * e
     *
     * 解释：
     * 'e' 是那个被添加的字母。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-the-difference
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        char ans = 0;
        for (char c : s.toCharArray()) {
            ans ^= c;
        }
        for (char c : t.toCharArray()) {
            ans ^= c;
        }
        return ans;
    }
}

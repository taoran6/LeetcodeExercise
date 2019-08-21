package com.example.leetcode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 只出现一次的数字
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
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

    public int singleNumber2(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans = ans ^ num;
        }
        return ans;
    }


}

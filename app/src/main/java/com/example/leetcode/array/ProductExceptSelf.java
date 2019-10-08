package com.example.leetcode.array;

import java.util.Arrays;

/**
 * 除自身以外数组的乘积
 */
public class ProductExceptSelf {
    /**
     * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 
     * nums[i] 之外其余各元素的乘积。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,2,3,4]
     * 输出: [24,12,8,6]
     * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     * <p>
     * 进阶：
     * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 方法一：使用两个数组存储左右积
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        //存储所有的左积
        int[] front = new int[n];
        front[0] = nums[0];
        //存储所有的右积
        int[] back = new int[n];
        back[n - 1] = nums[n - 1];

        //计算所有的左积和右积
        for (int i = 1; i < n; i++) {
            front[i] = front[i - 1] * nums[i];
            back[n - i - 1] = back[n - i] * nums[n - i - 1];
        }

        //左右积相乘
        for (int i = 1; i < n - 1; i++) {
            ans[i] = front[i - 1] * back[i + 1];
        }
        ans[0] = back[1];
        ans[n - 1] = front[n - 2];
        return ans;
    }

    /**
     * 方法二：进阶，使用常数空间复杂度
     *
     * https://leetcode-cn.com/problems/product-of-array-except-self/solution/cheng-ji-dang-qian-shu-zuo-bian-de-cheng-ji-dang-q/
     */
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        int k = 1;
        for (int i = 0; i < n; i++) {
            ans[i] = k;
            k = k * nums[i];    // 此时数组存储的是除去当前元素左边的元素乘积
        }

        k = 1;
        for (int i = n - 1; i >= 0; i --) {
            ans[i] = ans[i] * k;    // k为该数右边的乘积。
            k = k * nums[i];    //此时数组等于左边的 * 该数右边的。
        }

        return ans;
    }

    /**
     * 方法三：只遍历一次,其实时间复杂度都是O(n)，跟方法二一样，看看就好了
     */
    public int[] productExceptSelf3(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        int left = 1; int right = 1;
        Arrays.fill(ans, 1);
        for(int i = 0; i < n; i++) {
            ans[i] = ans[i] * left;
            left = left * nums[i];

            ans[n - 1 - i] = right * ans[n - 1 - i];
            right = right * nums[n - i - 1];
        }
        return ans;
    }
}

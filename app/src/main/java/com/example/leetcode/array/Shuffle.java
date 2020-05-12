package com.example.leetcode.array;

import java.util.Random;

/**
 * 洗牌算法
 */
public class Shuffle {

    /**
     * 生成[min, max]之间的随机整数
     * @param min
     * @param max
     * @return
     */
    private int random(int min, int max) {
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(max-min+1) + min;
    }

    /**
     * 四种洗牌算法
     * @param nums
     */
    public void shuffle1(int[] nums) {
        int n = nums.length;
        /******** 区别只有这两行 ********/
        for(int i = 0; i < n; i++) {
            // 从 i 到最后随机选一个元素
            int rand = random(i, n-1);
            swap(nums, i, rand);
        }
    }

    public void shuffle2(int[] nums) {
        int n = nums.length;
        /******** 区别只有这两行 ********/
        for(int i = 0; i < n - 1; i++) {
            int rand = random(i, n-1);
            swap(nums, i, rand);
        }
    }

    public void shuffle3(int[] nums) {
        int n = nums.length;
        /******** 区别只有这两行 ********/
        for(int i = n - 1; i >= 0; i--) {
            int rand = random(i, 0);
            swap(nums, i, rand);
        }
    }

    public void shuffle4(int[] nums) {
        int n = nums.length;
        /******** 区别只有这两行 ********/
        for(int i = n - 1; i > 0; i--) {
            int rand = random(i, 0);
            swap(nums, i, rand);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

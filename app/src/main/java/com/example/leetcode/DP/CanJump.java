package com.example.leetcode.DP;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 跳跃游戏
 */
public class CanJump {
    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 判断你是否能够到达最后一个位置。
     *
     * 示例 1:
     *
     * 输入: [2,3,1,1,4]
     * 输出: true
     * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
     * 示例 2:
     *
     * 输入: [3,2,1,0,4]
     * 输出: false
     * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/jump-game
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean canJump(int[] nums) {
        // !!!这种方法超时了
        int length = nums.length;
        if(length <= 1) return true;

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> canArrive = new HashSet<>();
        queue.offer(0);

        while (!queue.isEmpty()){
            int i = queue.poll();
            int number = nums[i];
            if(i + number >= length - 1) return true;
            else {
                for(int k = 1; k <= number; k++) {
                    if(!queue.contains(k + i)) {
                        canArrive.add(k + i);
                        queue.offer(k + i);
                    }
                }
            }

        }
        return false;
    }

    /**
     * 贪心算法
     * 从右向左遍历，时间复杂度O（n）
     *
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        int length = nums.length;
        if(length <= 1) return true;

        int max = length - 1;   //max表示若当前能跳到max位置，就一定能跳到最后一个位置
        for(int i =length - 2 ; i >= 0; i--) {
            if(nums[i] >= max - i){
                max = i;
            }
        }
        return max == 0;
    }

}

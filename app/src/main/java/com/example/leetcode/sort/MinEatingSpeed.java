package com.example.leetcode.sort;

/**
 *
 * 二分查找应用
 */
public class MinEatingSpeed {
    /**
     * 875. 爱吃香蕉的珂珂
     *
     * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
     *
     * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉
     * 少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
     *
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     *
     * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
     *
     *  
     *
     * 示例 1：
     *
     * 输入: piles = [3,6,7,11], H = 8
     * 输出: 4
     * 示例 2：
     *
     * 输入: piles = [30,11,23,4,20], H = 5
     * 输出: 30
     * 示例 3：
     *
     * 输入: piles = [30,11,23,4,20], H = 6
     * 输出: 23
     *  
     *
     * 提示：
     *
     * 1 <= piles.length <= 10^4
     * piles.length <= H <= 10^9
     * 1 <= piles[i] <= 10^9
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/koko-eating-bananas
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int minEatingSpeed(int[] piles, int H) {
        //最速度是堆中最大的值，最小值是1
        int min = 1;
        int max = 1;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }

        //二分法查找边界
        while (min < max) {
            int mid = min + (max - min) / 2;    //防止溢出
            boolean canFinishMid = canFinish(piles, H, mid);
            if(!canFinishMid && canFinish(piles, H, mid + 1)) {
                return mid + 1;
            }

            if(!canFinishMid) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }

        return min;
    }

    /**
     * 在 H 小时内以 K 的速度是否能吃完
     * @param piles
     * @param H
     * @param K
     * @return
     */
    public boolean canFinish(int[] piles, int H, int K) {
        int time = 0;
        for(int pile : piles) {
            time += pile / K;
            if((pile % K) != 0) time++;

            if(time > H) return false;
        }
        return true;
    }

    /**
     * 1011. 在 D 天内送达包裹的能力
     *
     * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
     *
     * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
     *
     * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
     * 输出：15
     * 解释：
     * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
     * 第 1 天：1, 2, 3, 4, 5
     * 第 2 天：6, 7
     * 第 3 天：8
     * 第 4 天：9
     * 第 5 天：10
     *
     * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成
     * (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
     * 示例 2：
     *
     * 输入：weights = [3,2,2,4,1,4], D = 3
     * 输出：6
     * 解释：
     * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
     * 第 1 天：3, 2
     * 第 2 天：2, 4
     * 第 3 天：1, 4
     * 示例 3：
     *
     * 输入：weights = [1,2,3,1,1], D = 4
     * 输出：3
     * 解释：
     * 第 1 天：1
     * 第 2 天：2
     * 第 3 天：3
     * 第 4 天：1, 1
     *  
     *
     * 提示：
     *
     * 1 <= D <= weights.length <= 50000
     * 1 <= weights[i] <= 500
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int shipWithinDays(int[] weights, int D) {
        int min = 0;
        int max = 0;
        for(int weight : weights) {
            min = Math.max(min, weight);
            max += weight;
        }

        while (min < max) {
            int mid = min + (max - min) / 2;

            if(! canLoad(weights, D, mid)) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }

        return min;
    }

    public boolean canLoad(int[] weights, int D, int ship) {
        int day = 1;
        int aShip = ship;

        for (int weight : weights) {
            if(weight <= aShip) {
                aShip = aShip - weight;
            } else {
                aShip = ship - weight;
                day ++;
                if(day > D) return false;
            }
        }

        return true;
    }


}

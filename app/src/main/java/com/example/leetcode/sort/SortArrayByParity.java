package com.example.leetcode.sort;

/**
 * 按奇偶排序数组
 */
public class SortArrayByParity {
    /**
     * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
     *
     * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
     *
     * 你可以返回任何满足上述条件的数组作为答案。
     *
     *  
     *
     * 示例：
     *
     * 输入：[4,2,5,7]
     * 输出：[4,5,2,7]
     * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
     *  
     *
     * 提示：
     *
     * 2 <= A.length <= 20000
     * A.length % 2 == 0
     * 0 <= A[i] <= 1000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param A
     * @return
     *
     * 方法一：开辟新的空间
     */
    public int[] sortArrayByParityII(int[] A) {
        int length = A.length;
        int half = length / 2;
        //用数组存储比用ArrayList快
        int[] A1 = new int[half];   //存奇数
        int[] A2 = new int[half];   //存偶数

        int n1 = 0;
        int n2 = 0;
        for (int i = 0; i < length; i++) {
            if(A[i] % 2 != 0) A1[n1 ++] = A[i];
            else A2[n2 ++] = A[i];
        }

        int j = 0;
        for (int i = 0; i < half; i ++) {
            A[j ++] = A2[i];
            A[j ++] = A1[i];
        }
        return A;
    }

    /**
     * 我们可能会被面试官要求写出一种不需要开辟额外空间的解法。
     *
     * 在这个问题里面，一旦所有偶数都放在了正确的位置上，那么所有奇数也一定都在正确的位子上。所以只需要关注
     * A[0], A[2], A[4], ... 都正确就可以了。
     *
     * 将数组分成两个部分，分别是偶数部分 even = A[0], A[2], A[4], ... 和奇数部分 odd = A[1], A[3],
     * A[5], ...。定义两个指针 i 和 j, 每次循环都需要保证偶数部分中下标 i 之前的位置全是偶数，奇数部分中下
     * 标 j 之前的位置全是奇数。
     *
     * 算法
     *
     * 让偶数部分下标 i 之前的所有数都是偶数。为了实现这个目标，把奇数部分作为暂存区，不断增加指向奇数部分的指针，
     * 直到找到一个偶数，然后交换指针 i，j 所指的数。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii/solution/an-qi-ou-pai-xu-shu-zu-ii-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int[] sortArrayByParityII2(int[] A) {
        int j = 1;

        for (int i = 0; i < A.length; i = i + 2) {
            if(A[i] % 2 != 0) {     //A[i] 不是偶数
                while (A[j] % 2 != 0) {
                    j = j + 2;
                }

                //交换
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }

        return A;
    }
}

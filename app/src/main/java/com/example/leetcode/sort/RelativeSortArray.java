package com.example.leetcode.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 数组的相对排序
 */
public class RelativeSortArray {
    /**
     * 给你两个数组，arr1 和 arr2，
     * <p>
     * arr2 中的元素各不相同
     * arr2 中的每个元素都出现在 arr1 中
     * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
     * <p>
     * 示例：
     * <p>
     * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
     * 输出：[2,2,2,1,4,3,3,9,6,7,19]
     *  
     * <p>
     * 提示：
     * <p>
     * arr1.length, arr2.length <= 1000
     * 0 <= arr1[i], arr2[i] <= 1000
     * arr2 中的元素 arr2[i] 各不相同
     * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/relative-sort-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 方法一：使用HashMap
     */

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], 0);
        }

        int[] others = new int[1000];
        int otherIndex = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (map.containsKey(arr1[i])) {
                map.put(arr1[i], map.get(arr1[i]) + 1);     //arr2计数
            } else {
                others[otherIndex++] = arr1[i];     //剩下的单独放一个数组
            }
        }

        int count = 0;
        int[] ans = new int[arr1.length];
        //先添加arr2里面的数
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < map.get(arr2[i]); j++) {
                ans[count++] = arr2[i];
            }
        }

        //再添加arr1中剩下的
        Arrays.sort(others, 0, otherIndex);
        for (int i = 0; i < otherIndex; i++) {
            ans[count + i] = others[i];
        }
        return ans;
    }

    /**
     * 方法二：计数排序
     *
     * 根据提示
     * arr1.length, arr2.length <= 1000
     * 0 <= arr1[i], arr2[i] <= 1000
     */
    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        int[] m = new int[1001];
        int[] ans = new int[arr1.length];

        //计数
        for (int i = 0; i < arr1.length; i++) {
            m[arr1[i]] ++;
        }

        int count = 0;
        //先添加arr2里面的数
        for (int i = 0; i < arr2.length; i++) {
            while (m[arr2[i]] > 0) {
                ans[count ++] = arr2[i];
                m[arr2[i]] --;
            }
        }
        //再添加arr1中剩下的
        for (int i = 0; i < 1001; i++) {
            while (m[i] > 0) {
                ans[count ++] = i;
                m[i] --;
            }
        }
        return ans;
    }
}

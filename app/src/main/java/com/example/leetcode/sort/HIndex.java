package com.example.leetcode.sort;

import java.util.Arrays;

/**
 * 274. H指数
 */
public class HIndex {
    /**
     * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
     *
     * h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）
     * 至多有 h 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次。）”
     *
     *  
     *
     * 示例:
     *
     * 输入: citations = [3,0,6,1,5]
     * 输出: 3
     * 解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
     *      由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
     *  
     *
     * 说明: 如果 h 有多种可能的值，h 指数是其中最大的那个。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/h-index
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        //这里0的情况要考虑
        if(citations.length == 0) return 0;
        Arrays.sort(citations);
        int ans = 1;
        //这里要判断citations.length - ans > 0防止数组越界
        while (citations.length - ans >= 0 && citations[citations.length - ans] >= ans) {
            ans++;
        }
        return ans - 1;
    }

    /**
     * 方法二：桶排序
     *
     * 论文的引用次数可能会非常多，这个数值很可能会超过论文的总数 n，因此使用计数排序是非常不合算的（会超出空间
     * 限制）,由于 h 指数一定小于等于 n, 所以引用次数大于 n 的等价于引用次数为 n。
     */
    public int hIndex2(int[] citations) {
        int n = citations.length;

        int[] counts = new int[n + 1];

        for (int paper : citations) {
            if(paper > n) counts[n] ++;
            else if(paper >= 0)counts[paper] ++;
        }

        int sum = 0;
        for (int i = n; i >= 0; i --) {
            //计算引用次数大于等于i的论文篇数
            sum += counts[i];
            if(sum >= i) return i;
        }
        return 0;
    }
}

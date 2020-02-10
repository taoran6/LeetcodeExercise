package com.example.leetcode.math;

/**
 * 整数中1出现的次数（从1到n整数中1出现的次数）
 */
public class NumberOf1Between1AndN {
    /**
     * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有
     * 1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,可以
     * 很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
     *
     *
     * 设i为计算1所在的位数，i=1表示计算个位数的1的个数，10表示计算十位数的1的个数等等。
     *
     * k = n % (i * 10)
     * count(i) = (n / (i * 10)) * i + (if(k > i * 2 - 1) i else if(k < i) 0 else k - i + 1)
     *
     * 链接：https://www.nowcoder.com/questionTerminal/bd7f978302044eee894445e244c7eee6?f=discussion
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        if(n <= 0)
            return 0;

        long count = 0;
        for(long i = 1; i <= n; i *= 10){
            //使用long防止溢出
            long div = i * 10;
            count += (n / div) * i;
            long k = n % div;
            if(k >= i * 2) count += i;
            else if(k < i) count += 0;
            else count += k - i + 1;
        }
        return (int)count;
    }
}

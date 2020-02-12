package com.example.leetcode.array;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 扑克牌顺子
 */
public class IsContinuous {
    /**
     * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...他随
     * 机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！“红
     * 心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,他想了想,决定大\小 王可以看成任何
     * 数字,并且A看作1,J为11,Q为12,K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),
     * “So Lucky!”。LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何，
     * 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0
     * @param numbers
     * @return
     *
     * 链接：https://www.nowcoder.com/questionTerminal/762836f4d43d43ca9deb273b3de8e1f4?f=discussion
     * 来源：牛客网
     *
     * max 记录 最大值
     * min 记录  最小值
     * min ,max 都不记0
     * 满足条件 1 max - min <5
     *         2 除0外没有重复的数字(牌)
     *         3 数组长度 为5
     */
    public boolean isContinuous(int [] numbers) {
        if(numbers == null || numbers.length == 0) return false;

        Set<Integer> set = new HashSet<>();
        int max = 0;
        int min = 0;
        for (int i : numbers) {
            if(i != 0) {
                if(!set.add(i)) return false;
                if(i > max) max = i;
                if(min == 0 || i < min) min = i;
            }
        }
        return (max - min) < 5;
    }
}

package com.example.leetcode.string;

/**
 * 报数
 */
public class CountAndSay {

    /**
     * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
     *
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 1 被读作  "one 1"  ("一个一") , 即 11。
     * 11 被读作 "two 1s" ("两个一"）, 即 21。
     * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
     *
     * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
     *
     * 注意：整数顺序将表示为一个字符串。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: 1
     * 输出: "1"
     * 示例 2:
     *
     * 输入: 4
     * 输出: "1211"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/count-and-say
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        String[] array = new String[31];
        array[1] = "1";
        for (int i = 2; i <= 30; i++) {
            int count = 1;
            array[i] = "";
            for(int j = 0; j < array[i-1].length() - 1; j++) {
                if(array[i-1].charAt(j) != array[i-1].charAt(j+1)) {
                    array[i] = array[i] + count + array[i-1].charAt(j);
                    count = 1;
                } else {
                    count ++;
                }
            }
            array[i] = array[i] + count + array[i-1].charAt(array[i-1].length() - 1);
        }
        return array[n];
    }

    /**
     * 改进：1.只遍历n遍
     *      2.使用StringBuilder会快一个数量级
     *      3.使用两个临时变量，不需要数组
     * @param n
     * @return
     */
    public String countAndSay2(int n) {
        if(n == 0) return null;
        String tmp = "";
        String out = "1";
        for (int i = 2; i <= n; i++) {
            int count = 1;
            tmp = out;
            StringBuilder builder = new StringBuilder();
            for(int j = 0; j < tmp.length() - 1; j++) {
                if(tmp.charAt(j) != tmp.charAt(j+1)) {
                    builder.append(count).append(tmp.charAt(j));
                    count = 1;
                } else {
                    count ++;
                }
            }
            out = builder.append(count).append(tmp.charAt(tmp.length() - 1)).toString();
        }
        return out;
    }
}

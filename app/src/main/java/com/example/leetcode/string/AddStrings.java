package com.example.leetcode.string;

/**
 * 字符串相加
 */
public class AddStrings {
    /**
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     *
     * 注意：
     *
     * num1 和num2 的长度都小于 5100.
     * num1 和num2 都只包含数字 0-9.
     * num1 和num2 都不包含任何前导零。
     * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-strings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public String addStrings(String num1, String num2) {
        if(num1.length() > num2.length()) {
            String tmp = num1;
            num1 = num2;
            num2 = tmp;
        }

        char[] num1a = num1.toCharArray(); int l1 = num1.length();
        char[] num2a = num2.toCharArray(); int l2 = num2.length();

        int flag = 0;
        StringBuilder builder = new StringBuilder();
        for(int i = 1; i <= l1; i ++) {
            int sum = num1a[l1 - i] + num2a[l2 - i] - '0' - '0' + flag;
            flag = sum / 10;
            builder.insert(0, sum % 10);
        }
        for (int i = l1 + 1; i <= l2; i++) {
            int sum = num2a[l2 - i] + flag - '0';
            flag = sum / 10;
            builder.insert(0, sum % 10);
        }
        if(flag != 0) builder.insert(0, 1);
        return builder.toString();
    }
}

package com.example.leetcode.string;

/**
 *  验证回文串
 */
public class Palindrome {
    public boolean isPalindrome(String s) {
        if(s.length() == 0) return true;
        int head = 0;
        int tail = s.length() - 1;
        while (head < tail) {
            char h = s.charAt(head);
            if( ! isValidChar(h)){
                head ++;
                continue;
            }
            char t = s.charAt(tail);
            if(! isValidChar(t)) {
                tail --;
                continue;
            }

            //注意特殊情况"0P", 不能单纯的使用ASCII数值相减！！！
            // （'0'-'P'的值正好等于'a'- A'的值，好坑）
            h = toLowerCase(h);
            t = toLowerCase(t);
            if(h != t) return false;
            tail--;
            head++;

        }

        return true;
    }

    private boolean isValidChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }
    private char toLowerCase(char c) {
        if (c >= 'A' && c <= 'Z') return (char) (c - 'A' + 'a');
        return c;
    }
}

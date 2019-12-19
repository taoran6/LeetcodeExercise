package com.example.leetcode.math;

/**
 * Nim 游戏
 */
public class CanWinNim {
    /**
     * 你和你的朋友，两个人一起玩 Nim 游戏：桌子上有一堆石头，每次你们轮流拿掉 1 - 3 块石头。 拿掉最后一块石头
     * 的人就是获胜者。你作为先手。
     *
     * 你们是聪明人，每一步都是最优解。 编写一个函数，来判断你是否可以在给定石头数量的情况下赢得游戏。
     *
     * 示例:
     *
     * 输入: 4
     * 输出: false
     * 解释: 如果堆中有 4 块石头，那么你永远不会赢得比赛；
     *      因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/nim-game
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 要想获胜，在你的回合中，必须避免石头堆中的石子数为 4 的情况。
     * 同样地，如果有五块、六块、或是七块石头，你可以控制自己拿取的石头数，总是恰好给你的对手留下四块石头，
     * 使他输掉这场比赛。但是如果石头堆里有八块石头，你就不可避免地会输掉，因为不管你从一堆石头中挑出一块、两块
     * 还是三块，你的对手都可以选择三块、两块或一块，以确保在再一次轮到你的时候，你会面对四块石头。
     *
     * 巴什博奕，n%(m+1)!=0时，先手总是会赢的
     *
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
        return (n % 4) != 0;
    }

    /**
     * 299. 猜数字游戏
     *
     * 你正在和你的朋友玩 猜数字（Bulls and Cows）游戏：你写下一个数字让你的朋友猜。每次他猜测后，你给他一个提
     * 示，告诉他有多少位数字和确切位置都猜对了（称为“Bulls”, 公牛），有多少位数字猜对了但是位置不对（称为
     * “Cows”, 奶牛）。你的朋友将会根据提示继续猜，直到猜出秘密数字。
     *
     * 请写出一个根据秘密数字和朋友的猜测数返回提示的函数，用 A 表示公牛，用 B 表示奶牛。
     *
     * 请注意秘密数字和朋友的猜测数都可能含有重复数字。
     *
     * 示例 1:
     *
     * 输入: secret = "1807", guess = "7810"
     *
     * 输出: "1A3B"
     *
     * 解释: 1 公牛和 3 奶牛。公牛是 8，奶牛是 0, 1 和 7。
     * 示例 2:
     *
     * 输入: secret = "1123", guess = "0111"
     *
     * 输出: "1A1B"
     *
     * 解释: 朋友猜测数中的第一个 1 是公牛，第二个或第三个 1 可被视为奶牛。
     * 说明: 你可以假设秘密数字和朋友的猜测数都只包含数字，并且它们的长度永远相等。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/bulls-and-cows
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public String getHint(String secret, String guess) {
        int[] secCount = new int[10];
        int[] gussCount = new int[10];
        char[] sec = secret.toCharArray();
        char[] guss = guess.toCharArray();
        int bull = 0, cow = 0;

        for (int i = 0; i < secret.length(); i++) {
            if(sec[i] == guss[i]) bull++;
            secCount[sec[i] - '0'] ++;
            gussCount[guss[i] - '0'] ++;
        }
        for (int i = 0; i < 10; i++) {
            cow += Math.min(secCount[i], gussCount[i]);
        }

        return bull + "A" + (cow - bull) + "B";
    }
}

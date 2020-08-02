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

    /**
     * 877. 石子游戏
     *
     * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
     *
     * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
     *
     * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有
     * 更多的石子堆为止，此时手中石子最多的玩家获胜。
     *
     * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
     *
     *  
     *
     * 示例：
     *
     * 输入：[5,3,4,5]
     * 输出：true
     * 解释：
     * 亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
     * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
     * 如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
     * 如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
     * 这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
     *  
     *
     * 提示：
     *
     * 2 <= piles.length <= 500
     * piles.length 是偶数。
     * 1 <= piles[i] <= 500
     * sum(piles) 是奇数。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/stone-game
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     * 如果我们把这四堆石头按索引的奇偶分为两组，即第 1、3 堆和第 2、4 堆， 那么这两组石头的数量一定不同，也就是
     * 说一堆多一堆少。因为石头的总数 是奇数，不能被平分。
     *
     * 而作为第一个拿石头的人，你可以控制自己拿到所有偶数堆，或者所有的奇 数堆。
     * 你最开始可以选择第 1 堆或第 4 堆。如果你想要偶数堆，你就拿第 4 堆，这 样留给对手的选择只有第 1、3 堆，
     * 他不管怎么拿，第 2 堆又会暴露出来， 你就可以拿。同理，如果你想拿奇数堆，你就拿第 1 堆，留给对手的只有
     * 第 2、4 堆，他不管怎么拿，第 3 堆又给你暴露出来了。
     * 也就是说，你可以在第一步就观察好，奇数堆的石头总数多，还是偶数堆的 石头总数多，然后步步为营，就一切尽在掌控之中了。
     *
     * 也就是你第一个拿奇数堆，剩下的最外面肯定是偶数堆给对手拿
     */
    public boolean stoneGame(int[] piles) {
        return true;
    }

    /**
     * 319. 灯泡开关
     *
     * 初始时有 n 个灯泡关闭。 第 1 轮，你打开所有的灯泡。 第 2 轮，每两个灯泡你关闭一次。 第 3 轮，每三个灯泡
     * 切换一次开关（如果关闭则开启，如果开启则关闭）。第 i 轮，每 i 个灯泡切换一次开关。 对于第 n 轮，你只切换
     * 最后一个灯泡的开关。 找出 n 轮后有多少个亮着的灯泡。
     *
     * 示例:
     *
     * 输入: 3
     * 输出: 1
     * 解释:
     * 初始时, 灯泡状态 [关闭, 关闭, 关闭].
     * 第一轮后, 灯泡状态 [开启, 开启, 开启].
     * 第二轮后, 灯泡状态 [开启, 关闭, 开启].
     * 第三轮后, 灯泡状态 [开启, 关闭, 关闭].
     *
     * 你应该返回 1，因为只有一个灯泡还亮着。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/bulb-switcher
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 因为6 = 2*3 = 1*6  因子都是成对出现的，所以一般情况下一定是关
     * 但是例外的情况是平方数 16 = 1*16 = 2*8 = 4*4 只有5个因子，是奇数次，
     * 所以只需要考虑n以内有多少个完全平方数即可
     */
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}

package com.example.leetcode.design;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Twitter {
    /**
     * 355. 设计推特
     *
     * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）
     * 的最近十条推文。你的设计需要支持以下的几个功能：
     *
     * postTweet(userId, tweetId): 创建一条新的推文
     * getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必
     * 须按照时间顺序由最近的开始排序。
     * follow(followerId, followeeId): 关注一个用户
     * unfollow(followerId, followeeId): 取消关注一个用户
     * 示例:
     *
     * Twitter twitter = new Twitter();
     *
     * // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
     * twitter.postTweet(1, 5);
     *
     * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
     * twitter.getNewsFeed(1);
     *
     * // 用户1关注了用户2.
     * twitter.follow(1, 2);
     *
     * // 用户2发送了一个新推文 (推文id = 6).
     * twitter.postTweet(2, 6);
     *
     * // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
     * // 推文id6应当在推文id5之前，因为它是在5之后发送的.
     * twitter.getNewsFeed(1);
     *
     * // 用户1取消关注了用户2.
     * twitter.unfollow(1, 2);
     *
     * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
     * // 因为用户1已经不再关注用户2.
     * twitter.getNewsFeed(1);
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/design-twitter
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    /**
     * 存储用户id与用户的映射关系
     */
    Map<Integer, User> userMap;

    //本来时间戳应该用System.currentTimeMillis();但是在；LeetCode测试中由于两次操作时间间隔过短。时间戳相等了
    int timeStamp = 0;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        userMap = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        // 如果不存在需要新建用户
        if (!userMap.containsKey(userId)) {
            createUser(userId);
        }
        // 封装，全部交给User去处理
        timeStamp++;
        userMap.get(userId).postTwitter(tweetId, timeStamp);
    }

    /**
     * 创建新用户
     *
     * @param userId
     */
    private void createUser(int userId) {
        User user = new User(userId);
        userMap.put(userId, user);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed
     * must be posted by users who the user followed or by the user herself. Tweets must be ordered
     * from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        if (!userMap.containsKey(userId)) {
            createUser(userId);
        }
        List<Integer> ans = new ArrayList<>();

        //合并k个排序链表的算法
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.tweet.timestamp - o2.tweet.timestamp > 0) {
                    return -1;
                } else if (o1.tweet.timestamp - o2.tweet.timestamp == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        for (int followId : userMap.get(userId).followSet) {
            ListNode node = userMap.get(followId).tweetList;
            if (node != null) {
                priorityQueue.offer(node);
            }
        }
        while (!priorityQueue.isEmpty() && ans.size() < 10) {
            ListNode node = priorityQueue.poll();
            ans.add(node.tweet.tweetId);
            if (node.next != null) {
                priorityQueue.add(node.next);
            }
        }
        return ans;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            createUser(followerId);
        }
        if (!userMap.containsKey(followeeId)) {
            createUser(followeeId);
        }

        userMap.get(followerId).follow(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        //follower 取关 followee，如果 Id 不存在则什么都不做
        if (userMap.containsKey(followerId)) {
            userMap.get(followerId).unfollow(followeeId);
        }
    }


    /**
     * 推文类
     */
    static class Tweet {
        int tweetId;
        //时间戳
        long timestamp;

        Tweet(int id, int time) {
            tweetId = id;
            timestamp = time;
        }
    }

    /**
     * 用户类
     */
    static class User {
        int userId;
        Set<Integer> followSet;
        ListNode tweetList;

        User(int id) {
            userId = id;
            followSet = new HashSet<>();
            //自己也要关注
            followSet.add(userId);
            tweetList = null;
        }

        void postTwitter(int id, int time) {
            Tweet newTweet = new Tweet(id, time);
            ListNode newNode = new ListNode(newTweet);
            //添加到链表头
            newNode.next = tweetList;
            tweetList = newNode;

        }

        void follow(int followeeId) {
            followSet.add(followeeId);
        }

        void unfollow(int followeeId) {
            //不可以取关自己
            if (userId != followeeId) {
                followSet.remove(followeeId);
            }
        }
    }

    /**
     * 推特链表节点
     */
    static class ListNode {
        Tweet tweet;
        ListNode next;

        ListNode(Tweet tweet) {
            this.tweet = tweet;
            this.next = null;
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */


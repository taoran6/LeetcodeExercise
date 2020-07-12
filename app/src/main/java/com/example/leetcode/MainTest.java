package com.example.leetcode;

import com.example.leetcode.array.FindUnsortedSubarray;
import com.example.leetcode.design.ExamRoom;
import com.example.leetcode.sort.MinEatingSpeed;
import com.example.leetcode.string.Calculate;

public class MainTest {
    public static void main(String[] args) {
        ExamRoom examRoom = new ExamRoom(10);
        int a1 = examRoom.seat();
        int a2 = examRoom.seat();
        int a3 = examRoom.seat();
        examRoom.leave(4);
        int a4 = examRoom.seat();
    }
}

package com.example.leetcode;

public enum Season {
    SPRING(1, "spring"), SUMMER(2, "summer"),
    AUTUMN(3, "autumn"), WINTER(4, "winter");

    private int value;
    private String label;

    private Season(int v, String s) {
        this.value = v;
        this.label = s;
    }
}

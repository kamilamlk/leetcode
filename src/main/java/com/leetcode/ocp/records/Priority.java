package com.leetcode.ocp.records;

import java.util.Arrays;

public enum Priority {
    ONE(1) {
        @Override
        public String toString() {
            return "LOW";
        }
    },
    TWO(2),

    THREE(3) {
        @Override
        public String toString() {
            return "NORMAL";
        }
    },
    FOUR(4),
    FIVE(5) {
        @Override
        public String toString() {
            return "HIGH";
        }
    };

    private int value;

    Priority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Priority.values()));
    }
}

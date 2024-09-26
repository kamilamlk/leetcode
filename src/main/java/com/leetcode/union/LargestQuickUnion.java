package com.leetcode.union;

public class LargestQuickUnion {
    private final int[] array;

    public LargestQuickUnion(int n) {
        this.array = new int[n];
        for (int i = 0; i < n; i++) {
            this.array[i] = i;
        }
    }

    private void validate(int i) {
        if (i < 0 || i >= this.array.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i < j) {
            this.array[i] = j;
        } else {
            this.array[j] = i;
        }
    }

    public int find(int i) {
        validate(i);
        while (i != this.array[i]) {
            this.array[i] = this.array[this.array[i]];
            i = this.array[i];
        }
        return i;
    }
}

package com.leetcode.union.successor;

public class Successor {
    private final int[] id;

    public Successor(int n) {
        this.id = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                this.id[i] = n - 1;
            } else {
                this.id[i] = i + 1;
            }
        }
    }

    private void validate(int i) {
        if (i < 0 || i >= this.id.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        if (this.id[i] == -1) {
            throw new IllegalArgumentException("Element is removed");
        }
    }

    public void remove(int i) {
        validate(i);
        this.id[i] = -1;
    }

    public int findSuccessor(int i) {
        validate(i);
        return this.id[i];
    }
}

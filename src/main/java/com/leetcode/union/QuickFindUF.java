package com.leetcode.union;

/**
 **   items are in one group (1, 2, 3, ...)
 **/
public class QuickFindUF {
    private final int[] array;
    private int count;

    /**
     * Time Complexity: O(n)
     * @param n number of elements
     */
    public QuickFindUF(int n) {
        this.array = new int[n];
        for (int i = 0; i < n; i++) {
            this.array[i] = i;
        }
        this.count = n;
    }

    public int count() {
        return this.count;
    }

    private void validate(int i) {
        if (i < 0 || i >= this.array.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
    }

    public boolean connected(int i, int j) {
        validate(i);
        validate(j);
        return this.array[i] == this.array[j];
    }

    /**
     * Time Complexity: O(1)
     * @param i is the element to find linked group
     * @return the group of the element
     */
    public int find(int i) {
        validate(i);
        return this.array[i];
    }

    /**
     * Time Complexity: O(n)
     * To process N union commands on N objects, it takes N^2 array accesses
     * @param i is the element to union
     * @param j is the element to union
     */
    public void union(int i, int j) {
        validate(i);
        validate(j);
        int iGroup = this.array[i];
        int jGroup = this.array[j];
        this.array[j] = iGroup;
        for (int k = 0; k < this.array.length; k++) {
            if (this.array[k] == jGroup) {
                this.array[k] = iGroup;
            }
        }
        this.count--;
    }
}

package com.leetcode.union;

/**
 * QuickUnion
 *      algo   | initialization | union | find | comment
 * ---------------------------------------
 * quick-union |   O(n)         | O(n) | O(n) | (connected via parent). Can be improved by adding weighting
 * ---------------------------------------
 * quick-find  |   O(n)         | O(n) | O(1) | are in one group (1, 2, 3, ...)
 */
public class QuickUnionUF {
    private final int[] array;

    public QuickUnionUF(int n) {
        this.array = new int[n];
        for (int i = 0; i < n; i++) {
            this.array[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    /**
     * Time complexity: O(n), includes finding roots
     * Connects two elements
     * @param p element 1
     * @param q element 2
     */
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        this.array[i] = j;
    }

    /**
     * Time complexity: O(n)
     * Finds root of the element
     * @param i element
     * @return root of the element
     */
    public int find(int i) {
        int j = this.array[i];
        while (j != this.array[j]) {
            j = this.array[j];
        }
        return j;
    }
}

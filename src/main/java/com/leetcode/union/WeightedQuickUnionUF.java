package com.leetcode.union;

public class WeightedQuickUnionUF {
    private final int[] array;
    private final int[] weight;
    private int count;
    public WeightedQuickUnionUF(int n) {
        this.array = new int[n];
        this.weight = new int[n];
        this.count = n;
        for (int i = 0; i < n; i++) {
            this.array[i] = i;
        }
    }

    public int getCount() {
        return count;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
    /**
     * Time complexity: O(n), includes finding roots
     * Connects two elements
     * @param p element 1
     * @param q element 2
     */
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (weight[i] < weight[j]) {
            this.array[i] = j;
            weight[j] += weight[i];
        } else {
            this.array[j] = i;
            weight[i] += weight[j];
        }
        this.count--;
    }

    /**
     * Time complexity: O(n)
     * Finds root of the element
     * @param i element
     * @return root of the element
     */
    public int root(int i) {
        while (i != this.array[i]) {
            i = this.array[i];
        }
        return i;
    }
}

package com.leetcode.union;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightedQuickUnionUFTest {
    @Test
    void test() {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(10);
        weightedQuickUnionUF.union(4, 3);
        weightedQuickUnionUF.union(3, 8);
        weightedQuickUnionUF.union(6, 5);
        weightedQuickUnionUF.union(9, 4);
        weightedQuickUnionUF.union(2, 1);
        assertTrue(weightedQuickUnionUF.connected(8, 9));
        assertFalse(weightedQuickUnionUF.connected(5, 4));
        weightedQuickUnionUF.union(5, 0);
        weightedQuickUnionUF.union(7, 2);
        weightedQuickUnionUF.union(6, 1);
        assertTrue(weightedQuickUnionUF.connected(1, 0));
        assertTrue(weightedQuickUnionUF.connected(6, 7));
        assertFalse(weightedQuickUnionUF.connected(7, 8));
    }
}
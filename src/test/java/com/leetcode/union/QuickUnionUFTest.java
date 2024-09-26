package com.leetcode.union;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickUnionUFTest {

    @Test
    void connected() {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.union(4, 3);
        quickUnionUF.union(3, 8);
        quickUnionUF.union(6, 5);
        quickUnionUF.union(9, 4);
        quickUnionUF.union(2, 1);
        assertTrue(quickUnionUF.connected(8, 9));
        assertFalse(quickUnionUF.connected(5, 4));
        quickUnionUF.union(5, 0);
        quickUnionUF.union(7, 2);
        quickUnionUF.union(6, 1);
        assertTrue(quickUnionUF.connected(1, 0));
        assertTrue(quickUnionUF.connected(6, 7));
        assertFalse(quickUnionUF.connected(7, 8));
    }

}
package com.leetcode.union;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class QuickFindUFTest {

    @Test
    void test() {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        Assertions.assertThat(quickFindUF.count()).isEqualTo(10);
        quickFindUF.union(4, 3);
        quickFindUF.union(3, 8);
        quickFindUF.union(6, 5);
        quickFindUF.union(9, 4);
        quickFindUF.union(2, 1);
        Assertions.assertThat(quickFindUF.connected(0, 7)).isFalse();
        Assertions.assertThat(quickFindUF.connected(8, 9)).isTrue();
        quickFindUF.union(5, 0);
        quickFindUF.union(7, 2);
        quickFindUF.union(6, 1);
        quickFindUF.union(1, 0);
        Assertions.assertThat(quickFindUF.connected(0, 7)).isTrue();
    }
}
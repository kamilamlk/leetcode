package com.leetcode.ocp.api.drawable;

public class IntExtractor {
    IntHolder makeIntHolder(final int i) {
        return new MyIntHolder(i);
    }



    static class MyIntHolder implements IntHolder {
        final int j;

        MyIntHolder(int i) {
            this.j = i;
        }

        public int getInt() {
            return j;
        }
    }
}

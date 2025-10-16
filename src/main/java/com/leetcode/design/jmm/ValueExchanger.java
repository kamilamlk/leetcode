package com.leetcode.design.jmm;

public class ValueExchanger {
    private int valA;
    private int valB;
    private int valC;

    public void set(Values v) {
        this.valA = v.valA;
        this.valB = v.valB;

        synchronized(this) {
            this.valC = v.valC;
        }
    }

    public void get(Values v) {
        synchronized(this) {
            v.valC = this.valC;
        }
        v.valB = this.valB;
        v.valA = this.valA;
    }
}

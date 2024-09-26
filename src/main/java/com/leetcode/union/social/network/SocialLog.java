package com.leetcode.union.social.network;

public class SocialLog {
    private final int timestamp;
    private final int p;
    private final int q;

    public SocialLog(int timestamp, int p, int q) {
        this.timestamp = timestamp;
        this.p = p;
        this.q = q;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public int getP() {
        return p;
    }

    public int getQ() {
        return q;
    }
}

package com.leetcode.union.social.network;

import com.leetcode.union.WeightedQuickUnionUF;

public class SocialNetworkConnectivity {
    public static int connect(int n, SocialLog[] logs) {
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        for (SocialLog log : logs) {
            uf.union(log.getP(), log.getQ());
            if (uf.getCount() == 1) {
                return log.getTimestamp();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SocialLog[] logs = new SocialLog[5];
        logs[0] = new SocialLog(1, 1, 2);
        logs[1] = new SocialLog(2, 2, 3);
        logs[2] = new SocialLog(3, 3, 4);
        logs[3] = new SocialLog(4, 4, 5);
        logs[4] = new SocialLog(5, 5, 6);
        var timestamp = connect(6, logs);
        System.out.println(timestamp);
    }
}

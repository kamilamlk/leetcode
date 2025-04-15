package com.leetcode.sliding.window;

import java.util.LinkedList;
import java.util.Queue;

public class New21Game {
    public double new21Game(int n, int k, int maxPts) {

        double[] dp = new double[n + 1];
        dp[0] = 1.0 ;// 100% sure in the first step
        double windowSum = 1.0;

        for(int i = 1; i <= n; i++) {
            // calculate current value as sum of previous values divided by maxPts
            dp[i] = windowSum / maxPts;
            if(i < k) {
                // increase sum while not reached k points
                windowSum += dp[i];
            } else if(i - maxPts >= 0 && i - maxPts < k) {
                // calculate window if reach k points
                windowSum -= dp[i - maxPts];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        New21Game new21Game = new New21Game();
        System.out.println(new21Game.new21Game(10, 1, 10)); // 1.0
        //System.out.println(new21Game.new21Game(6, 1, 10)); // 0.6
        //System.out.println(new21Game.new21Game(21, 17, 10)); // 0.73278
        //System.out.println(new21Game.new21Game(100, 50, 50)); // 0.9999999999999999
    }
}

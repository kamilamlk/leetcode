package com.leetcode.string;

public class BullsAndCows {
    public String getHint(String secret, String guess) {
        int[] digitBulls = new int[10];
        int[][] digitCows = new int[10][2];

        // 2n
        for(int i = 0; i < secret.length(); i++) {
            if(secret.charAt(i) == guess.charAt(i)) {
                digitBulls[secret.charAt(i) - '0']++;
            } else {
                digitCows[secret.charAt(i) - '0'][0]++; // secret
                digitCows[guess.charAt(i) - '0'][1]++; // guess
            }

        }

        int bulls = 0;
        int cows = 0;
        // 10n
        for(int i = 0; i < digitCows.length; i++) {
            bulls += digitBulls[i];
            cows += Math.min(digitCows[i][0], digitCows[i][1]); // get min
        }
        return "%sA%sB".formatted(bulls, cows);
    }

    public static void main(String[] args) {
        BullsAndCows solution = new BullsAndCows();
        //System.out.println(solution.getHint("1807", "7810")); // Output: "1A3B"
        System.out.println(solution.getHint("1123", "0111")); // Output: "1A1B"
        //System.out.println(solution.getHint("1", "0"));       // Output: "0A0B"
        //System.out.println(solution.getHint("1", "1"));       // Output: "1A0B"
    }
}

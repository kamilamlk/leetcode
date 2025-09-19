package com.leetcode.tree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

public class UniqueBinarySearchTrees {
    private static class Trie {
        char c;
        Trie[] subtries;
        boolean end;

        Trie(char c, boolean end) {
            this.subtries = new Trie[26];
            this.c = c;
            this.end = end;
        }
    }

    public int numTrees(int n) {
        return countSubTrees(1, n);
    }

    private int countSubTrees(int start, int end) {
        return 0;
    }

    public static void main(String[] args) {
//        UniqueBinarySearchTrees u = new UniqueBinarySearchTrees();
//        int n = 3;
//        System.out.println(u.numTrees(n)); // Output: 5

        PriorityQueue<Integer> stream = new PriorityQueue<>();
        stream.addAll(List.of(1, 2, 3, 4, 5));
        int mid = (stream.size() + 1) / 2 - 1;

        for(int num : stream) {
            if (mid == 0) {
                System.out.println(num);
                break;
            }
            mid--;
        }

        var random = new Random();
        StringBuilder sb = new StringBuilder();

        sb.delete(sb.length() - 1, sb.length());
        Trie trie = new Trie(' ', false);
    }

    private static class Tweet {
        int tweetId;
        int addedAt;
        int userId;

        Tweet(int tweetId, int userId, int addedAt) {
            this.tweetId = tweetId;
            this.userId = userId;
            this.addedAt = addedAt;
        }
    }
}

package com.leetcode.queue.trie;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, n, k, 0, result, new ArrayList<>());
        return result;
    }

    private void dfs(int prev,
                     int n,
                     int k,
                     int index,
                     List<List<Integer>> result,
                     List<Integer> current) {
        if(k == index) {
            if(current.size() != k) {
                return;
            }
            result.add(new ArrayList<>(current));
        }
        for(int i = prev + 1; i <= n; i++) {
            current.add(i);
            dfs(i, n, k, index + 1, result, current);
            current.remove(current.size() - 1);
        }
    }
}

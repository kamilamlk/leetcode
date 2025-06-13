package com.leetcode.queue.tree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinimumGeneticMutation {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Queue<String> queue = new LinkedList<>();
        Set<String> bankSet = new HashSet<>();
        bankSet.addAll(Arrays.asList(bank));
        char[] geneChars = new char[]{'A', 'C', 'G', 'T'};
        queue.add(startGene);
        int mutations = 0;
        while (!queue.isEmpty()) {
            var current = queue.poll();
            if (current.equals(endGene)) {
                return mutations;
            }
            for (int i = 0; i < current.length(); i++) {
                char[] currentChars = current.toCharArray();
                for (char geneChar : geneChars) {
                    if (currentChars[i] == geneChar) {
                        continue; // Skip if the character is the same
                    }
                    currentChars[i] = geneChar;
                    String newGene = new String(currentChars);
                    if (bankSet.contains(newGene)) {
                        queue.add(newGene);
                        bankSet.remove(newGene); // Prevent re-visiting
                    }
                }
            }
            mutations++;
        }
        return -1; // If no mutation path found
    }

    public static void main(String[] args) {
        MinimumGeneticMutation solution = new MinimumGeneticMutation();
        String startGene = "AACCGGTT";
        String endGene = "AACCGGTA";
        String[] bank = {"AACCGGTA"};
        int result = solution.minMutation(startGene, endGene, bank);
        System.out.println("Minimum mutations required: " + result); // Output: 1
    }
}

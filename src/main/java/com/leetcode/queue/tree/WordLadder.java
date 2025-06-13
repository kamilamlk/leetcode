package com.leetcode.queue.tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
    public int ladderLength(String beginWord,
                            String endWord,
                            List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return 0; // If endWord is not in the wordList, return 0
        }
        queue.add(beginWord);
        int steps = 1; // Start with the first word as a step
        while (!queue.isEmpty()) {
            int size = queue.size(); // Get the current size of the queue
            for (int i = 0; i < size; i++) {
                var current = queue.poll();
                if (current.equals(endWord)) {
                    return steps; // Found the endWord, return the number of steps
                }
                char[] currentChars = current.toCharArray();
                for (int k = 0; k < currentChars.length; k++) {
                    char originalChar = currentChars[k];
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (originalChar == j) {
                            continue; // Skip if the character is the same
                        }
                        currentChars[k] = j; // Change the character
                        String newWord = new String(currentChars);
                        if (words.contains(newWord)) {
                            queue.add(newWord); // Add the new word to the queue
                            words.remove(newWord); // Remove it from the set to prevent re-visiting
                        }
                    }
                    currentChars[k] = originalChar; // Restore the original character
                }
            }
            steps++; // Increment the step count after processing all transformations for the current word
        }
        return 0; // If no transformation sequence found, return 0
    }

    public static void main(String[] args) {
        WordLadder solver = new WordLadder();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        int result = solver.ladderLength(beginWord, endWord, wordList);
        System.out.println("Length of the shortest transformation sequence: " + result); // Output: 5
    }
}

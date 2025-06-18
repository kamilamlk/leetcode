package com.leetcode.queue.trie;

public class Trie {
    private final Trie[] children;

    public Trie() {
        this.children = new Trie[27];
    }

    public void insert(String word) {
        insert(word, 0);
    }

    private void insert(String word, int index) {
        if (index == word.length()) {
            children[26] = new Trie(); // Mark the end of the word
            return;
        }
        char c = word.charAt(index);
        if(children[c - 'a'] == null) {
            children[c - 'a'] = new Trie();
        }
        children[c - 'a'].insert(word, index + 1);
    }

    public boolean search(String word) {
        return search(word, 0);
    }

    private boolean search(String word, int index) {
        if(index == word.length()) {
            return children[26] != null; // Check if the end of the word is marked
        }
        char c = word.charAt(index);
        if (children[c - 'a'] == null) {
            return false; // Not found
        }
        return children[c - 'a'].search(word, index + 1);
    }

    public boolean startsWith(String prefix) {
        return startsWith(prefix, 0);
    }

    private boolean startsWith(String prefix, int index) {
        if (index == prefix.length()) {
            return true; // Found the prefix
        }
        char c = prefix.charAt(index);
        if (children[c - 'a'] == null) {
            return false; // Prefix not found
        }
        return children[c - 'a'].startsWith(prefix, index + 1);
    }
}

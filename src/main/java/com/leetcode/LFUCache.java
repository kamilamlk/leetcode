package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LFUCache {
    Map<Integer, Node> map;
    Map<Integer, LinkedList<Node>> counter;
    int capacity;
    int minFrequency = Integer.MAX_VALUE;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        counter = new HashMap<>();
    }

    /**
     get element
     */
    public int get(int key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            increment(node);
            return node.value;
        }
        return -1;
    }

    /**
     if exists increment
     else
     resize
     countNew
     */
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            increment(node);
        } else {
            resize();
            Node node = new Node(key, value);
            map.put(key, node);
            increment(node);
        }
    }

    private void increment(Node node) {
        node.counter++;
        minFrequency = Math.min(minFrequency, node.counter);
        if(!counter.containsKey(node.counter)) {
            counter.put(node.counter, new LinkedList<Node>());
        }
        counter.get(node.counter).add(node);
    }

    private void resize() {
        while(map.size() >= capacity) {
            LinkedList<Node> nodes = counter.get(minFrequency);
            while(!nodes.isEmpty()) {
                Node node = nodes.getFirst();
                if(node.counter != minFrequency) { // cleanup list
                    nodes.removeFirst();
                } else {
                    map.remove(node.key);
                    nodes.removeFirst();
                    return;
                }
            }
            minFrequency++;
        }
    }


    private static class Node {
        int key;
        int value;

        int counter;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.counter = 0;
        }
    }


    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1); // cache is {1=1}
        cache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(cache.get(1)); // return 1
        cache.put(3, 3); // evicts key 2, cache is {1=1, 3=3}
        System.out.println(cache.get(2)); // returns -1 (not found)
        cache.put(4, 4); // evicts key 1, cache is {3=3, 4=4}
        System.out.println(cache.get(1)); // returns -1 (not found)
        System.out.println(cache.get(3)); // returns 3
        System.out.println(cache.get(4)); // returns 4
    }
}
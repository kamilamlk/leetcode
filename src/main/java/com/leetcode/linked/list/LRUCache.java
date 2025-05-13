package com.leetcode.linked.list;

import com.leetcode.queue.stack.Node;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    Map<Integer, Node> cache;
    int capacity;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        makeHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
        } else {
            node = new Node(key, value);
            cache.put(key, node);
        }
        makeHead(node);
        resize();
    }
    /*
        * 1. remove node from current position
        *       can be done by removing the next and prev pointers
        * 2. add node to head
        * 3. update head and tail
     */
    private void makeHead(Node node) {
        if (node == head) {
            return;
        }
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (node == tail) {
            tail = node.prev;
        }
        node.prev = null;
        node.next = head;

        if (head != null) {
            head.prev = node;
        }
        head = node;

        if (tail == null) {
            tail = node;
        }
    }

    private void resize() {
        if (cache.size() > capacity) {
            cache.remove(tail.key);
            Node prev = tail.prev;
            prev.next = null;
            tail = prev;
        }
    }

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(3, 2);
        System.out.println(lruCache.get(3) + " == " + 2); // return 2
        System.out.println(lruCache.get(2) + " == " + 1); // return 1
        lruCache.put(4, 3); // evicts key 3
        System.out.println(lruCache.get(2) + " == " + 1); // return 1
        System.out.println(lruCache.get(3) + " == " + -1); // return -1 (not found)
        System.out.println(lruCache.get(4) + " == " + 3); // return 3

    }
}
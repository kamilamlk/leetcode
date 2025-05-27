package com.leetcode.linked.list;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class LRUCache {
    private final int capacity;
    private final Map<Integer,Node> cache;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        balance(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node;
        if (cache.containsKey(key)) {
            node = cache.get(key);
            node.value = value;
        } else {
            node = new Node(key, value);
            head.prev = node;
            node.next = head;
            head = node;
            cache.put(key, node);
        }
        balance(node);

        resize();
    }

    private void balance(Node node) {
        if (node.next == null && node.prev == null) { // new node
            makeHead(node);
        } else if (node.next != null && node.prev != null) { // somewhere in the middle
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;

            makeHead(node);
        } else if (node == tail) { // tail
            Node prev = node.prev;
            prev.next = null;
            node.prev = null;
            // make head;
            makeHead(node);
        }
    }

    private void makeHead(Node node) {
        Node next = head;
        head.prev = node;
        node.next = next;
        node.prev = null;
        head = node;
    }

    private void resize() {
        if (cache.size() == capacity ) {
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


}

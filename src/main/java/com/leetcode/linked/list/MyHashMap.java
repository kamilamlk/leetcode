package com.leetcode.linked.list;

import com.leetcode.pointers.ListNode;

import java.util.HashMap;

public class MyHashMap {
    private static class MapNode {
        int key;
        int value;
        MapNode next;

        public MapNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    MapNode[] nodes = new MapNode[100];
    private int capacity = 100;

    public MyHashMap() {
    }

    public void put(int key, int value) {
        int index = key % capacity;
        MapNode node = nodes[index];
        if (node == null) {
            nodes[index] = new MapNode(key, value);
        } else {
            MapNode current = node;
            MapNode prev = null;
            while (current != null) {
                if (current.key == key) {
                    current.value = value;
                    return;
                } else {
                    prev = current;
                    current = current.next;
                }
            }
            prev.next = new MapNode(key, value);
        }
    }

    public int get(int key) {
        int index = key % capacity;
        MapNode node = nodes[index];
        while (node != null) {
            if (node.key == key) {
                return node.value;
            } else {
                node = node.next;
            }
        }
        return -1;
    }

    public void remove(int key) {
        int index = key % capacity;

        MapNode node = nodes[index];
        MapNode prev = null;
        while (node != null) {
            if (node.key == key) {
                if (prev == null) {
                    nodes[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                return;
            } else {
                prev = node;
                node = node.next;
            }
        }
    }

    public static void main(String[] args) {
        //["MyHashMap","put","put","get","get","put","get","remove","get"]
        //[[],[1,1],[2,2],[1],[3],[2,1],[2],[2],[2]]
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1);
        System.out.print(null + " ");
        myHashMap.put(2, 2);
        System.out.print(null + " ");
        System.out.print(myHashMap.get(1) + " ");
        System.out.print(myHashMap.get(3) + " ");
        myHashMap.put(2, 1);
        System.out.print(null + " ");
        System.out.print(myHashMap.get(2) + " ");
        myHashMap.remove(2);
        System.out.print(null + " ");
        System.out.print(myHashMap.get(2) + " ");
    }
}

package com.leetcode.design;

public class MyLinkedList {
    private class Node {
        int val;
        Node prev;
        Node next;

        Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val + "," + (next!= null ? next.toString() : "");
        }
    }
    Node dummyHead;
    Node dummyTail;
    int size;

    public MyLinkedList() {
        this.dummyHead = new Node(0);
        this.dummyTail = new Node(0);

        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
        size = 0;
    }

    public int get(int index) {
        if(index >= size) return -1;
        Node node = getNode(index);
        return node != null ? node.val : -1;
    }

    private Node getNode(int index) {
        Node node = dummyHead;
        int i = -1;
        do {
            node = node.next;
            i++;
        } while (i < index);
        return node;
    }

    public void addAtHead(int val) {
        Node current = new Node(val);
        Node next = dummyHead.next;

        connect(dummyHead, current, next);
        size++;
    }

    private void connect(Node prev, Node current, Node next) {
        prev.next = current;
        current.prev = prev;

        next.prev = current;
        current.next = next;
    }

    public void addAtTail(int val) {
        Node current = new Node(val);
        Node prev = dummyTail.prev;
        connect(prev, current, dummyTail);
        size++;
    }

    public void addAtIndex(int index, int val) {
        if(index > size) return;
        Node next = getNode(index);
        Node prev = next.prev;

        Node current = new Node(val);
        connect(prev, current, next);
        size++;
    }

    public void deleteAtIndex(int index) {
        if(index >= size) return;
        Node current = getNode(index);
        Node prev = current.prev;
        Node next = current.next;

        prev.next = next;
        next.prev = prev;

        current.next = null;
        current.prev = null;
        size--;
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
        System.out.println(myLinkedList.get(1));              // return 2
        myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
        System.out.println(myLinkedList.get(1));              // return 3
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
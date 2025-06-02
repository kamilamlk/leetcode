package com.leetcode.ocp.generics;

public class Node<E> {
    private E data;
    private Node<E> next;

    public Node(final E data, final Node<E> next) {
        this.data = data;
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public void setData(final E data) {
        this.data = data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(final Node<E> next) {
        this.next = next;
    }

    public void type() {
        System.out.println("Node type: " + data.getClass().getName());
    }

    @Override
    public String toString() {
        return "LegacyNode{" +
            "data=" + data +
            ", next=" + next +
            '}';
    }
}

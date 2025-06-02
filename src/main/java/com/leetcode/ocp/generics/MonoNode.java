package com.leetcode.ocp.generics;

public class MonoNode<T> implements IMonoLink<T> {
    protected T value;
    private MonoNode<T> next;

    public MonoNode(T value) {
        this.value = value;
        this.next = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public MonoNode<T> getNext() {
        return next;
    }

    public void setNext(MonoNode<T> next) {
        this.next = next;
    }
}

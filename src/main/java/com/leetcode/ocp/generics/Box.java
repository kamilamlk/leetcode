package com.leetcode.ocp.generics;

public class Box<T> {
    private T item;


//    Box(T item) {
//        this.item = item;
//    }
//
//    <V> Box(V item) {
//        this.item = (T) item; // unchecked cast, use with caution
//    }
//    <V> Box (T t, V v) {
//
//    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Box{" +
                "item=" + item +
                '}';
    }
}

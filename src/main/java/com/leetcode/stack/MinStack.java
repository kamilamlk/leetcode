package com.leetcode.stack;

public class MinStack {
    private static class StackEntity {
        private int val;
        StackEntity prev;

        StackEntity(int val) {
            this.val = val;
        }
    }

    StackEntity min;
    StackEntity top;

    public MinStack() {

    }

    public void push(int val) {
        var entity = new StackEntity(val);
        if (min == null && top == null) {
            min = entity;
            top = entity;
        } else if (top == null){
            top = entity;
            top.prev = min;
        } else {
            entity.prev = top;
            top = entity;
        }

    }

    public void pop() {
        top = top.prev;
    }

    public int top() {
        return top.val;
    }

    public int getMin() {
        return min.val;
    }
}

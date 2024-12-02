package com.leetcode.queue.conclusion;

import java.util.Stack;

public class FIFO {
    class MyQueue {
        Stack<Integer> deque;
        Stack<Integer> queue;

        public MyQueue() {
            deque = new Stack<>();
            queue = new Stack<>();
        }

        public void push(int x) {
            deque.push(x);
        }

        public int pop() {
            transfer();
            return queue.pop();
        }

        public int peek() {
            transfer();
            return queue.peek();
        }

        private void transfer() {
            if (queue.isEmpty()) {
                while ((!deque.isEmpty())) {
                    queue.push(deque.pop());
                }
            }
        }

        public boolean empty() {
            return deque.isEmpty() && queue.isEmpty();
        }
    }

    public static void main(String[] args) {

        MyQueue myQueue = new FIFO().new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        System.out.println(myQueue.peek()); // return 1
        System.out.println(myQueue.pop()); // return 2
        System.out.println(myQueue.empty()); // return false
    }
}

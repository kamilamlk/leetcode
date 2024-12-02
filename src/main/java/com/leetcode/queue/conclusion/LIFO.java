package com.leetcode.queue.conclusion;

import java.util.Queue;

public class LIFO {
    class MyStack {
        Queue<Integer> queue;
        Queue<Integer> deque;

        public MyStack() {
            queue = new java.util.LinkedList<>();
            deque = new java.util.LinkedList<>();
        }

        public void push(int x) {
            queue.add(x);
        }

        private void transfer() {
            if (queue.isEmpty() && !deque.isEmpty()) {
                while (!deque.isEmpty()) {
                    queue.add(deque.poll());
                }
            }

            while (queue.size() > 1) {
                deque.add(queue.poll());
            }
        }

        public int pop() {
            transfer();
            return queue.poll();
        }

        public int top() {
            transfer();
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty() && deque.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyStack myStack = new LIFO().new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top()); // return 1
        System.out.println(myStack.pop()); // return 1
        System.out.println(myStack.empty()); // return false

        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        System.out.println(myStack.pop()); // return 2
        System.out.println(myStack.pop()); // return 3
        System.out.println(myStack.pop()); // return 1
    }
}

package com.leetcode.queue;

import java.util.Queue;

public class CircularQueue {
    static class MyCircularQueue {
        private int[] queue;
        private int head = 0;
        private int tail = -1;
        private int size = 0;
        public MyCircularQueue(int k) {
            this.queue = new int[k];
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }

            tail = (tail + 1) % queue.length;
            queue[tail] = value;
            size++;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            head = (head + 1) % queue.length;
            size--;
            return true;
        }

        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return queue[head];
        }

        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            return queue[tail];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == queue.length;
        }
    }

    public static void main(String[] args) {
        MyCircularQueue myCircularQueue = new MyCircularQueue(3);
        System.out.println(myCircularQueue.enQueue(1)); // return True
        System.out.println(myCircularQueue.enQueue(2)); // return True
        System.out.println(myCircularQueue.enQueue(3)); // return True
        System.out.println(myCircularQueue.enQueue(4)); // return False
        System.out.println(myCircularQueue.Rear());     // return 3
        System.out.println(myCircularQueue.isFull());   // return True
        System.out.println(myCircularQueue.deQueue());  // return True
        System.out.println(myCircularQueue.enQueue(4)); // return True
        System.out.println(myCircularQueue.Rear());     // return 4

        Queue<Integer> queue;
    }
}

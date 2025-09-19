package com.leetcode.design;

import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFinder {
    Queue<Integer> maxQueue;
    Queue<Integer> minQueue;

    public MedianFinder() {
        maxQueue = new PriorityQueue<>((a, b) -> b - a);
        minQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a));
    }

    public void addNum(int num) {
        maxQueue.offer(num);

        // Step 2: balance order (largest of left -> smallest of right)
        minQueue.offer(maxQueue.poll());

        // Step 3: maintain size property (maxQueue >= minQueue)
        if (maxQueue.size() < minQueue.size()) {
            maxQueue.offer(minQueue.poll());
        }
    }

    public double findMedian() {
        if (maxQueue.size() == minQueue.size()) {
            return (maxQueue.peek() + minQueue.peek()) / 2.0;
        } else {
            return maxQueue.peek();
        }
    }

    public static void main(String[] args) {
        /**
         * ["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"]
         *
         * [[],[6],[],[10],[],[2],[],[6],[],[5],[],[0],[],[6],[],[3],[],[1],[],[0],[],[0],[]]
         */
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(6);
        System.out.println("6.0 - " + medianFinder.findMedian()); // return 6.0
        medianFinder.addNum(10);
        System.out.println("8.0 - " + medianFinder.findMedian()); // return 8.0
        medianFinder.addNum(2);
        System.out.println("6.0 - " + medianFinder.findMedian()); // return 6.0
        medianFinder.addNum(6);
        System.out.println("6.0 - " + medianFinder.findMedian()); // return 6.0
        medianFinder.addNum(5);
        System.out.println("6.0 - " + medianFinder.findMedian()); // return 6.0
        medianFinder.addNum(0);
        System.out.println("5.5 - " + medianFinder.findMedian()); // return 5.5
        medianFinder.addNum(6);
        System.out.println("6.0 - " + medianFinder.findMedian()); // return 6.0
        medianFinder.addNum(3);
        System.out.println("6.0 - " + medianFinder.findMedian()); // return 6.0
        medianFinder.addNum(1);
        System.out.println("5.0 - " + medianFinder.findMedian()); // return 5.0
        medianFinder.addNum(0);
        System.out.println("5.0 - " + medianFinder.findMedian()); // return 5.0
        medianFinder.addNum(0);
        System.out.println("5.0 - " + medianFinder.findMedian()); // return 5.0

        Deque<Integer> pings;

    }
}

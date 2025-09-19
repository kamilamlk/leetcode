package com.leetcode.design;

import java.util.PriorityQueue;

public class KthLargest {
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
    int k;

    public KthLargest(int k, int[] nums) {
        this.maxHeap = new PriorityQueue<Integer>();
        this.minHeap = new PriorityQueue<Integer>((a, b) -> b - a);
        this.k = k;
        for(int num: nums) {
            minHeap.offer(num);
        }
        while(!minHeap.isEmpty() && maxHeap.size() < k) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public int add(int val) {
        minHeap.offer(val);
        while(!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.peek() > maxHeap.peek()) {
            int temp = minHeap.poll();
            maxHeap.offer(temp);
            minHeap.offer(maxHeap.poll());
        }
        while(!minHeap.isEmpty() && maxHeap.size() < k) {
            maxHeap.offer(minHeap.poll());
        }
        while(!maxHeap.isEmpty() && maxHeap.size() > k) {
            minHeap.offer(maxHeap.poll());
        }
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{4,5,8,2});
        System.out.println(kthLargest.add(3));   // return 4
        System.out.println(kthLargest.add(5));   // return 5
        System.out.println(kthLargest.add(10));  // return 5
        System.out.println(kthLargest.add(9));   // return 8
        System.out.println(kthLargest.add(4));   // return 8
    }
}

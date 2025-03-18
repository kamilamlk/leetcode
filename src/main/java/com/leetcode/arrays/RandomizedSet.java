package com.leetcode.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


/**
 * The need for O(1) random access complicates this exercise slightly.
 * You need a hashmap for O(1) insertions and removals and an integer array for the O(1) random access.
 * The hashmap entries should include an index with each value to show where it is in the array.
 *
 * One way to ensure O(1) removal from the array is to move just
 * the end element to where the gap is (instead of shifting all the elements over).
 * Don't forget to store the new index for this particular value.
 */
public class RandomizedSet {
    private int max;
    private int lastIndex;
    private int[] array;
    private Map<Integer, Integer> idxMap;
    private Random random;

    public RandomizedSet() {
        this.max = 100;
        this.lastIndex = 0;
        this.array = new int[max];
        this.idxMap = new HashMap<>();
        this.random = new Random();
    }

    private void resize() {
        max *= 2;
        array = Arrays.copyOf(array, max);
    }

    /**
     ~O(1)
     Inserts an item val into the set if not present.
     Returns true if the item was not present, false otherwise.
     */
    public boolean insert(int val) {
        if (idxMap.containsKey(val)) {
            return false;
        }
        if(lastIndex == max) {
            resize();
        }
        array[lastIndex] = val;
        idxMap.put(val, lastIndex++);
        return true;
    }

    /**
     O(1)
     Removes an item val from the set if present. Returns true if the item was present, false otherwise.
     */
    public boolean remove(int val) {
        if (!idxMap.containsKey(val)) {
            return false;
        }

        var idx = idxMap.get(val);
        if (lastIndex == 1) {
            idxMap.remove(val);
            lastIndex--;
        } else {
            array[idx] = array[--lastIndex];
            idxMap.remove(val);
            idxMap.put(array[idx], idx);
        }
        return true;
    }

    /**
     O(1)
     */
    public int getRandom() {
        return array[random.nextInt(lastIndex)];
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        // ["RandomizedSet","remove","remove","insert","getRandom","remove","insert"]
        // [[],[0],[0],[0],[],[0],[0]]
        System.out.println(randomizedSet.remove(0)); // false
        System.out.println(randomizedSet.remove(0)); // false
        System.out.println(randomizedSet.insert(0)); // true
        System.out.println(randomizedSet.getRandom()); // 0
        System.out.println(randomizedSet.remove(0)); // true
        System.out.println(randomizedSet.insert(0)); // true
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

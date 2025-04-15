package com.leetcode.sliding.window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FruitIntoBusket {

    public int totalFruit(int[] fruits) {
        int max = 0;
        int left = 0;
        int right = 0;
        Map<Integer, Integer> types = new HashMap<>();

        System.out.println('z' - 'A');

        while(right < fruits.length) {
            if(types.containsKey(fruits[right])) {
                types.put(fruits[right], types.get(fruits[right]) + 1);
                right++;
            } else if (types.size() < 2) {
                types.put(fruits[right], 1);
                right++;
            } else {
                max = Math.max(right - left, max);

                while (left < right && types.size() >= 2) {
                    if(types.get(fruits[left]) > 1) {
                        types.put(fruits[left], types.get(fruits[left]) - 1);
                    } else {
                        types.remove(fruits[left]);
                    }
                    left++;
                }

                types.put(fruits[right], 1);
                right++;
            }
        }
        max = Math.max(right - left, max);
        return max;
    }


    public static void main(String[] args) {
        FruitIntoBusket fruitIntoBusket = new FruitIntoBusket();

        System.out.println(fruitIntoBusket.totalFruit(new int[] {0,1,2,2}));
        System.out.println(fruitIntoBusket.totalFruit(new int[] {1,2,1}));
        System.out.println(fruitIntoBusket.totalFruit(new int[] {1,2,3,2,2}));
        System.out.println(fruitIntoBusket.totalFruit(new int[] {3,3,3,1,2,1,1,2,3,3,4}));
    }
}

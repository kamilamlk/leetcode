package com.leetcode.arrays;

/**
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * 2 + 15 = 17 > 9
 * 2 + 11 = 13 > 9
 * 2 + 7 = 9
 * 2 + 2 = 4 < 9
 *
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * 2 + 4 = 6
 *
 */
public class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            var sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[] {left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        TwoSumII twoSumII = new TwoSumII();
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSumII.twoSum(numbers, target);
        System.out.println(result[0] + " " + result[1]);

        int[] numbers2 = {2, 3, 4};
        int target2 = 6;
        result = twoSumII.twoSum(numbers2, target2);
        System.out.println(result[0] + " " + result[1]);
    }
}

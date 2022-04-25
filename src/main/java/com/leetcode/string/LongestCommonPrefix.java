package com.leetcode.string;

import java.util.Arrays;

public class LongestCommonPrefix {
	public static String longestCommonPrefix(String[] strs) {
		String result = "";
		int min = strs[0].length();
		int i = 0;

		while(i < min) {
			char current = Character.MIN_VALUE;
			for (String str: strs) {
				if (str.length() < min) min = str.length();
				if (str.length() <= i) {
					current = Character.MIN_VALUE;
					break;
				}
				if (current == Character.MIN_VALUE) {
					current = str.charAt(i);
				} else if (current != str.charAt(i)) {
					current = Character.MIN_VALUE;
					break;
				}
			}

			if (current == Character.MIN_VALUE) break;
			result = result.concat(String.valueOf(current));
			i++;
		}
		return result;
	}

	public static void main(String[] args) {
		String[] strings = new String[]{"abab","aba","" };
		System.out.println(longestCommonPrefix(strings));
	}
}

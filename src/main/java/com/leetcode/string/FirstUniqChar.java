package com.leetcode.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FirstUniqChar {
	private static class Pair<T> {
		T key;
		T value;

		public Pair(T key, T value) {
			this.key = key;
			this.value = value;
		}
	}

	public static int firstUniqChar(String s) {
		Map<Character, Pair<Integer>> mapping = new HashMap<>(s.length());

		for (int i = 0; i < s.length(); i++) {
			Pair<Integer> pair = mapping.getOrDefault(s.charAt(i), new Pair<>(i, 0));
			pair.value+=1;
			mapping.put(s.charAt(i), pair);
		}

		return mapping.values().stream()
				.filter(p -> p.value == 1)
				.map(p -> p.key)
				.min(Integer::compareTo).orElse(-1);
	}

	public static void main(String[] args) {
		System.out.println(firstUniqChar("aabb"));
	}
}

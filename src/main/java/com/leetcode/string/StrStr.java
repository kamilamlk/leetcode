package com.leetcode.string;

public class StrStr {
	public static int strStr(String haystack, String needle) {
		int j = 0;
		int index = -1;
		if (needle.length() == 0) return 0;
		if (needle.length() > haystack.length()) return -1;
		for (int i = 0; i < haystack.length(); i++) {
			if (haystack.charAt(i) == needle.charAt(j)) {
				if (j == 0) index = i;
				if (needle.length() == (j+1)) return index;
				j+=1;
			} else {
				if (index > -1) i = index ;
				index = -1;
				j = 0;
			}
		}
		if (j < needle.length()) return -1;
		if (index > (haystack.length() - needle.length() + 1)) return -1;
		return index;
	}

	public static void main(String[] args) {
		System.out.println(
				strStr("mississippi", "sippia")
		);
	}
}

package com.leetcode.string;

public class StringToInt {
	public static int myAtoi(String s) {
		int minDig = 48;
		int maxDig = 57;
		int hyphen = 45;
		int plus = 43;
		int diff = 48;
		int positivity = 1;
		boolean digitsStart = true;
		int num = 0;
		try {
			for (int i = 0; i < s.length(); i++) {
				int c = s.charAt(i);
				if (c == 32 && digitsStart) continue;
				if (c >= minDig && c <= maxDig) {
					digitsStart = false;
					num = Math.multiplyExact(num, 10);
					num = Math.addExact(num, (c-diff));
				} else if ((c == hyphen || c == plus) && num == 0 && digitsStart){
					digitsStart = false;
					if (c == hyphen) {
						positivity = -1;
					}
				} else break;

			}
		} catch (Exception e) {
			if (positivity == -1) return Integer.MIN_VALUE;
			else return Integer.MAX_VALUE;
		}

		return num * positivity;
	}

	public static void main(String[] args) {
		System.out.println(myAtoi("+-12"));
	}
}

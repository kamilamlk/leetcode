package com.leetcode.ocp.generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Wildcard {
    public static void main(String[] args) {
//        List<? extends Number> nums = List.of(1, 2, 4, 5);
//        List<? super Number> numbers = List.of(1, 2, 3, 4);
//
//        copy(numbers, nums);
//        //copy(nums, numbers); // ❌ This would cause a compile-time error
//
//        Node<?> anyNode = new Node<>(null, null); // Node of unknown type
//        Node<? extends Number> numberNode2 = new Node<Integer>(null, null); // Node of a specific type or its subtypes

        List<Number> numbers = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();

        copy(numbers, integers);
    }

    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        for (T item : src) {
            dest.add(item); // ✅ type-safe!
        }
    }
}

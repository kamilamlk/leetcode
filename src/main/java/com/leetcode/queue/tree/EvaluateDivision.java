package com.leetcode.queue.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluateDivision {
    private Map<String, Map<String, Double>> divisions = new HashMap<>();

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        fillDivisions(equations, values);
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            var query = queries.get(i);
            var first = query.get(0);
            var second = query.get(1);
            if (!divisions.containsKey(first) || !divisions.containsKey(second)) {
                result[i] = -1.0;
                continue;
            }
            if (first.equals(second)) {
                result[i] = 1.0;
                continue;
            }
            result[i] = divide(first, second, new HashSet<>(), 1.0);
        }
        return result;
    }

    private void fillDivisions(List<List<String>> equations, double[] values) {
        for(int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            var first = equation.get(0);
            var second = equation.get(1);
            addDivision(first, second, values[i]);
        }
    }

    private void addDivision(String first, String second, double value) {
        var firstMap = divisions.computeIfAbsent(first, k -> new HashMap<>());
        firstMap.putIfAbsent(second, value);
        firstMap.putIfAbsent(first, 1.0);
        var secondMap = divisions.computeIfAbsent(second, k -> new HashMap<>());
        secondMap.putIfAbsent(first, 1 / value);
        secondMap.putIfAbsent(second, 1.0);
    }

    private double divide(String key,
                          String target,
                          Set<String> visited,
                          double result) {
        if(key.equals(target)) {
            return result;
        }
        visited.add(key);
        var values = divisions.getOrDefault(key, new HashMap<>());
        for (var pair : values.entrySet()) {
            if (visited.contains(pair.getKey())) {
                continue;
            }
            var next = divide(
                pair.getKey(),
                target, visited,
                result * pair.getValue()
            );
            if (next != -1.0) {
                return next;
            }
        }
        return -1.0;
    }

    public static void main(String[] args) {
        EvaluateDivision evaluateDivision = new EvaluateDivision();
        List<List<String>> equations = List.of(
            List.of("a", "b"),
            List.of("b", "c")
        );
        double[] values = {2.0, 3.0};
        List<List<String>> queries = List.of(
            List.of("a", "c"),
            List.of("b", "a"),
            List.of("a", "e"),
            List.of("a", "a"),
            List.of("x", "x")
        );
        double[] results = evaluateDivision.calcEquation(equations, values, queries);
        for (double res : results) {
            System.out.println(res);
        }
    }
}

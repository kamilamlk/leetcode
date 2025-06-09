package com.leetcode.queue.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // Compute In-Degree for All Nodes
        for (var prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preCourse = prerequisite[1];
            inDegree[course]++;
            graph.computeIfAbsent(preCourse, k -> new ArrayList<>())
                .add(course);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);
            for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return order.size() == numCourses;
    }

    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        boolean result = cs.canFinish(numCourses, prerequisites);
        System.out.println("Can finish all courses: " + result); // Expected: true

        numCourses = 2;
        prerequisites = new int[][]{{1, 0}, {0, 1}};
        result = cs.canFinish(numCourses, prerequisites);
        System.out.println("Can finish all courses: " + result); // Expected: false
    }
}

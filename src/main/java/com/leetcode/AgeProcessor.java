package com.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class AgeProcessor {
    private class Employee {
        private String name;
        private int age;

        public Employee(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }
    }

    public String processEmployeeData(String input) {
        var employees = Arrays.stream(input.split(";"))
            .map(s -> {
                String[] arr = s.split(",");
                return new Employee(arr[0], Integer.parseInt(arr[1]));
            }).sorted(Comparator.comparing(Employee::getAge))
            .collect(Collectors.toList());
        var youngest = employees.get(0);
        var oldest = employees.get(employees.size() - 1);
        var mid = employees.get(employees.size() / 2);
        // Ваш код
        return youngest.getAge() + " " + mid.getAge() + " " + oldest.getAge();
    }

    public static void main(String[] args) {
        AgeProcessor ageProcessor = new AgeProcessor();
        System.out.println(ageProcessor.processEmployeeData("John,25;Jane,30;Alice,35"));
    }
}

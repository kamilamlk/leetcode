package com.leetcode.ocp.arrays;

public class TubeLight extends Light {
    String name;

    @Override
    public String describe() {
        return name + " " + starName;
    }
}

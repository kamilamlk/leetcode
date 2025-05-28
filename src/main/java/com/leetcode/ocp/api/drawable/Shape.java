package com.leetcode.ocp.api.drawable;

public class Shape implements IDrawable {
    @Override
    public void draw() {
        System.out.println("Drawing a shape");
    }
}

package com.leetcode.ocp.arrays;

public class InstanceCheck {
    public static void main(String[] args) {
        Object light = new TubeLight();
        System.out.println(light instanceof Light);
        System.out.println(light instanceof TubeLight);
        System.out.println(light instanceof Object);

        if (light instanceof Light t && t instanceof TubeLight tl) {
            System.out.println(t.getClass());
        }

        var clazz = light instanceof TubeLight t? t.getClass() : null;
        System.out.println(clazz);

        Light x = null;
        TubeLight y = null;
        y = (TubeLight) x;


        Light l = new Light();
        TubeLight tl = null;
        tl = (TubeLight) l;
    }
}

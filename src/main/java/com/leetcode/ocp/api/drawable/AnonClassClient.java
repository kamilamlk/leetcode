package com.leetcode.ocp.api.drawable;

public class AnonClassClient {
    public static void main(String[] args) {
        IDrawable[] drawables = {
            new Painter().createShape(),
            Painter.createStaticGraph(),
            new Painter().createStaticGraph()
        };

        for (IDrawable drawable : drawables) {
            drawable.draw();
            System.out.println(drawable.getClass().getName());
        }

        System.out.println("-------------------");
        System.out.println("Anonymous class Names: ");
//        System.out.println(drawables[0].getClass().getName());
//        System.out.println(drawables[1].getClass().getName());
//        System.out.println(drawables[2].getClass().getName());
    }
}

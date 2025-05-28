package com.leetcode.ocp.api.drawable;

public class Painter {
    private int nonStaticField = 10;
    private static int staticField = 20;

    public Shape createACircle(final double radius) {
        class Circle extends Shape {
            @Override
            public void draw() {
                System.out.println("Drawing a circle with radius: " + radius);
            }
        }
        return new Circle();
    }

    public static IDrawable createGraph() {
        final String localField = "Local field";

        class Helper {
            String getLocalField() {
                return localField;
            }
        }
        class Graph implements IDrawable {
            String localField = "Graph local field";
            @Override
            public void draw() {
                System.out.println(new Helper().getLocalField());
                System.out.println("Drawing a graph");
            }
        }
        return new Graph();
    }

    public Shape createShape() {
        return new Shape() {
            @Override
            public void draw() {
                System.out.println(nonStaticField);
                System.out.println("Drawing a shape from createShape");
            }
        };
    }

    public static IDrawable createStaticGraph() {
        int localField = 30;
        return new IDrawable() {
            @Override
            public void draw() {
                System.out.println(localField);
                System.out.println(staticField);
                System.out.println("Drawing a static graph");
            }
        };
    }
}

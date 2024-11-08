package com.leetcode;

public class ShapeCalculator {

    private enum Shape {
        CIRCLE("круг"), SQUARE("квадрат");
        private String name;

        Shape(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static Shape getShape(String name) {
            for (Shape shape : Shape.values()) {
                if (shape.getName().equals(name)) {
                    return shape;
                }
            }
            return null;
        }
    }

    private String calculate(Shape shape, Double side) {
        if (shape == null) {
            return "";
        }
        switch (shape) {
            case CIRCLE: {
                double perimeter = 2 * Math.PI * side;
                double area = Math.PI * Math.pow(side, 2);
                return String.format("%.2f %.2f", area, perimeter).replace(',', '.');
            }
            case SQUARE: {
                double perimeter = 4 * side;
                double area = Math.pow(side, 2);
                return String.format("%.2f %.2f", area, perimeter).replace(',', '.');
            }
            default:
                return "";
        }
    }

    public String calculate(String input) {
        String [] arr = input.split(" ");
        Shape shape = Shape.getShape(arr[0]);
        double side = arr.length == 2 ? Double.parseDouble(arr[1]) : 1;
        return calculate(shape, side);
    }

    public static void main(String[] args) {
        ShapeCalculator shapeCalculator = new ShapeCalculator();
        System.out.println(shapeCalculator.calculate("круг 3"));
        System.out.println(shapeCalculator.calculate("квадрат"));
    }
}

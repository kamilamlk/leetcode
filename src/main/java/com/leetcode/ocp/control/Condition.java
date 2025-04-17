package com.leetcode.ocp.control;

public class Condition {
    private static final int NO_ADVICE = 0;
    private static final int SOME_ADVICE = 1;
    private static final int LOTS_OF_ADVICE = 2;
    private static final char c = 'c';

    private enum SomeEnum {
        A, B, C
    }

    public static void main(String[] args) {
        char value = 3;
        String yield = switch (value) {
            case 2,3,5,7 -> "Prime";
            case 1,4,6,8 -> "Composite";
            default -> { yield "Unknown"; }
        };
        System.out.println("Yield: " + yield);
        //howMuchAdvice(SOME_ADVICE);

        days("MONDAY");
  //      enums(SomeEnum.A);
    }

//    private static void howMuchAdvice(int x) {
//        switch (x) {
//            case NO_ADVICE:
//                System.out.println("No advice");
//            default:
//                System.out.println("Default advice");
//            case SOME_ADVICE :
//                System.out.println("Some advice");
//            case LOTS_OF_ADVICE :
//                System.out.println("Lots of advice");
//        }
//    }

    private static void days(String day) {
        switch (day) {
            case "MONDAY" -> System.out.println("Start of week");
            case "FRIDAY" -> System.out.println("Almost weekend");
            case "SATURDAY" -> System.out.println("Weekend");
            default -> System.out.println("Midweek");
        }
    }

//    private static void enums(SomeEnum someEnum) {
//        switch (someEnum) {
//            case A -> System.out.println("A");
//            case B -> System.out.println("B");
//            case C -> System.out.println("C");
//        }
//    }
}

package com.leetcode.ocp.api;

import java.math.BigDecimal;
import java.util.Random;

public class MathClass {
    public static void main(String[] args) {
        BigDecimal price = new BigDecimal("2.99");
        BigDecimal tax = new BigDecimal("0.25");
        BigDecimal quantity = BigDecimal.TEN.pow(3);
        BigDecimal total = price.add(price.multiply(tax)).multiply(quantity);
        System.out.println("Total: " + total);

        double priceDouble = 2.99;
        double taxDouble = 0.25;
        double quantityDouble = Math.pow(10, 3);
        double totalDouble = (priceDouble + (priceDouble * taxDouble)) * quantityDouble;
        System.out.println("Total (double): " + totalDouble);


        System.out.println(Math.round(-0.5) + " " + Math.round(0.5));
    }
}

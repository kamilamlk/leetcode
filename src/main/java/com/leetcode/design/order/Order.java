package com.leetcode.design.order;

public record Order (
    int id,
    OrderType type,
    int units,
    Integer price
) {
    public Order leftover(int units) {
        return new Order(
            this.id,
            this.type,
            units,
            this.price
        );
    }
}

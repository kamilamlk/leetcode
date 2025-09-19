package com.leetcode.design.order;

public record Trade (
    int buyId,
    int sellId,
    int units,
    int price
){

    public static Trade of(Order buy, Order sell, int units) {
        return new Trade(
            buy.id(),
            sell.id(),
            units,
            sell.price() == null ? buy.price() : sell.price()
        );
    }
}

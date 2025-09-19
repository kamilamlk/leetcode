package com.leetcode.design.payment;

public record Transaction(
    String id,        // unique ID (UUID)
    String fromUser,  // null for deposits
    String toUser,    // null for withdrawals
    int amount,
    long timestamp
) {

    public static Transaction of(
        final String fromUser,
        final String toUser,
        final int amount
    ) {
        return new Transaction(
            java.util.UUID.randomUUID().toString(),
            fromUser,
            toUser,
            amount,
            System.currentTimeMillis()
        );
    }
}
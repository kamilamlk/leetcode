package com.leetcode.design.payment;

import com.leetcode.design.currency.exchange.Currency;

public record Transaction(
    String id,        // unique ID (UUID)
    String fromUser,  // null for deposits
    String toUser,    // null for withdrawals
    int amount,
    long timestamp,
    Currency currency
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
            System.currentTimeMillis(),
            Currency.USD
        );
    }

    public static Transaction of(
        final String id,
        final int amount,
        final Currency currency
    ) {
        return new Transaction(
            id,
            null,
            null,
            amount,
            System.currentTimeMillis(),
            currency
        );
    }


}
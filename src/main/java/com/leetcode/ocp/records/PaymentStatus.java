package com.leetcode.ocp.records;

public sealed interface PaymentStatus permits PaymentStatusEnum { }

enum PaymentStatusEnum implements PaymentStatus {
    PENDING,
    COMPLETED,
    FAILED;
}
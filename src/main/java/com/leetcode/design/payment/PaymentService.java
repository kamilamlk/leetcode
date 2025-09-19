package com.leetcode.design.payment;

import java.util.List;

public interface PaymentService {
    boolean deposit(String userId, int amount);
    boolean withdraw(String userId, int amount);
    String transfer(String fromUser, String toUser, int amount);

    int getBalance(String userId);
    Transaction getTransaction(String txId);
    List<Transaction> getUserTransactions(String userId);
}
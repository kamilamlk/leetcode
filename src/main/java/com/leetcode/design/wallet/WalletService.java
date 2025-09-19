package com.leetcode.design.wallet;

public interface WalletService {
    // deposit amount into user’s wallet
    boolean deposit(String userId, int amount);

    // withdraw amount from user’s wallet, return true if success, false if insufficient funds
    boolean withdraw(String userId, int amount);

    // transfer amount between two users atomically
    boolean transfer(String fromUser, String toUser, int amount);

    // get balance
    int getBalance(String userId);
}
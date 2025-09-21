package com.leetcode.design.currency.exchange;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class ConversionService {
    private final ExchangeRateService exchangeRateService;

    private final Map<String, Map<Currency, BigDecimal>> balances;
    private final Map<String, Map<Currency, ReentrantLock>> locks;

    public ConversionService(final ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
        this.balances = new ConcurrentHashMap<>();
        this.locks = new ConcurrentHashMap<>();
    }

    public boolean transfer(String fromUser,
                            String toUser,
                            BigDecimal fromAmount,
                            Currency fromCurrency,
                            Currency toCurrency) {

        BigDecimal toAmount = exchangeRateService.convertTo(fromAmount, fromCurrency, toCurrency);
        if (toAmount == null) return false;
        int compare = compare(fromUser, fromCurrency, toUser, toCurrency);
        if (compare == 0) return false;

        ReentrantLock firstLock = compare > 0 ? getLock(fromUser, fromCurrency) : getLock(toUser, toCurrency);
        ReentrantLock secondLock = compare > 0 ? getLock(toUser, toCurrency) : getLock(fromUser, fromCurrency);

        return withLocks(
            firstLock, secondLock,
            () -> {
                if (!balances.containsKey(fromUser)) {
                    throw new IllegalArgumentException("User not found");
                }
                BigDecimal balance = balances.get(fromUser).get(fromCurrency);
                if (balance == null || balance.compareTo(fromAmount) < 0) {
                    throw new IllegalArgumentException("Balance is insufficient");
                }
                balances.get(fromUser).put(fromCurrency, balance.subtract(fromAmount));
                deposit(toUser, toCurrency, toAmount);
                return true;
            }
        );
    }

    private ReentrantLock getLock(String user, Currency currency) {
        return locks.computeIfAbsent(user, k -> new ConcurrentHashMap<>())
            .computeIfAbsent(currency, k -> new ReentrantLock());
    }

    private boolean withLocks(ReentrantLock fromLock, ReentrantLock toLock, BooleanSupplier supplier) {
        try {
            if (!fromLock.tryLock(100, TimeUnit.MILLISECONDS)) {
                return false;
            }
            if (!toLock.tryLock(100, TimeUnit.MILLISECONDS)) {
                fromLock.unlock();
                return false;
            }
            return supplier.getAsBoolean();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (toLock.isHeldByCurrentThread()) {
                toLock.unlock();
            }
            if (fromLock.isHeldByCurrentThread()) {
                fromLock.unlock();
            }
        }
    }

    private int compare(String fromUser,
                      Currency fromCurrency,
                      String toUser,
                      Currency toCurrency) {
        if (fromUser.compareTo(toUser) == 0) {
            return fromCurrency.compareTo(toCurrency);
        }
        return fromUser.compareTo(toUser);
    }

    public void deposit(String user, Currency currency, BigDecimal amount) {
        withLock(
            locks.computeIfAbsent(user, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(currency, k -> new ReentrantLock()),

            () -> balances.computeIfAbsent(user, k -> new HashMap<>())
                .compute(currency,
                    (k, v) -> {
                        if (v == null) return amount;
                        return v.add(amount);
                    })
        );
    }

    public BigDecimal getBalance(String user, Currency currency) {
        return balances.computeIfAbsent(user, k -> new ConcurrentHashMap<>())
            .get(currency);
    }

    private <R> R withLock(ReentrantLock lock, Supplier<R> supplier) {
        try {
            if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
                return supplier.get();
            }

            return null;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}

package com.leetcode.design.currency.exchange;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ConversionServiceTest {

    @Test
    void shouldNotTransferForRateNotFound() {
        ConversionService cs = new ConversionService(new ExchangeRateMock(BigDecimal.ONE));
        Assertions.assertThat(
            cs.transfer(
                "user1", "user2",
                new BigDecimal("100"),
                Currency.EUR, Currency.RUB
            ))
            .isFalse();
    }

    @Test
    void shouldNotTransferForUserNotFound() {
        ConversionService cs = new ConversionService(new ExchangeRateMock(BigDecimal.ONE));
        Assertions.assertThatThrownBy(() -> cs.transfer(
            "user1", "user2",
            new BigDecimal("100"),
            Currency.EUR, Currency.EUR
        )).hasMessage("User not found");
    }

    @Test
    void shouldNotTransferForNoBalance() {
        ConversionService cs = new ConversionService(new ExchangeRateMock(BigDecimal.ONE));
        cs.deposit("user1", Currency.USD, new BigDecimal("100"));
        Assertions.assertThatThrownBy(() -> cs.transfer(
            "user1", "user2",
            new BigDecimal("100"),
            Currency.EUR, Currency.EUR
        )).hasMessage("Balance is insufficient");

        cs.deposit("user1", Currency.EUR, new BigDecimal("50"));
        Assertions.assertThatThrownBy(() -> cs.transfer(
            "user1", "user2",
            new BigDecimal("100"),
            Currency.EUR, Currency.EUR
        )).hasMessage("Balance is insufficient");
    }

    @Test
    void shouldTransfer() {
        ConversionService cs = new ConversionService(new ExchangeRateMock(BigDecimal.ONE));
        cs.deposit("user1", Currency.EUR, new BigDecimal("1000"));
        Assertions.assertThat(cs.transfer(
            "user1", "user2",
            new BigDecimal("100"),
            Currency.EUR, Currency.USD)
        ).isTrue();
        Assertions.assertThat(cs.getBalance("user1", Currency.EUR))
            .isEqualTo(new BigDecimal("900"));
        Assertions.assertThat(cs.getBalance("user2", Currency.USD))
            .isEqualTo(new BigDecimal("1"));
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 20, 50})
    void shouldHandleConcurrentTransfers(int threads) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        ConversionService cs = new ConversionService(new ExchangeRateMock(BigDecimal.TEN));
        cs.deposit("user1", Currency.EUR, new BigDecimal("1000"));
        cs.deposit("user2", Currency.USD, new BigDecimal("1000"));
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < threads; i++) {
            if (i % 2 == 0) {
                executorService.submit(() -> {
                    try {
                        latch.await();
                        cs.transfer(
                            "user1", "user2",
                            new BigDecimal("10"),
                            Currency.EUR, Currency.USD
                        );
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            } else {
                executorService.submit(() -> {
                    try {
                        latch.await();
                        cs.transfer(
                            "user2", "user1",
                            new BigDecimal("10"),
                            Currency.USD, Currency.EUR
                        );
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
        executorService.shutdown();
        latch.countDown();
        boolean completed = executorService.awaitTermination(10, TimeUnit.SECONDS);
        Assertions.assertThat(completed).isTrue();

        Assertions.assertThat(cs.getBalance("user1", Currency.EUR))
            .isEqualTo(new BigDecimal("1000"));
        Assertions.assertThat(cs.getBalance("user2", Currency.USD))
            .isEqualTo(new BigDecimal("1000"));
    }

    private static class ExchangeRateMock implements ExchangeRateService {
        BigDecimal mock;

        public ExchangeRateMock(BigDecimal mock) {
            this.mock = mock;
        }

        public BigDecimal convertTo(BigDecimal amount,
                                    Currency fromCurrency,
                                    Currency toCurrency) {
            if (fromCurrency == Currency.RUB || toCurrency == Currency.RUB) {
                return null;
            }
            return mock;
        }
    }
}
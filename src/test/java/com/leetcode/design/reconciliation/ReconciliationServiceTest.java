package com.leetcode.design.reconciliation;

import com.leetcode.design.currency.exchange.Currency;
import com.leetcode.design.payment.Transaction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ReconciliationServiceTest {

    @Test
    void shouldTestMismatch() {
        ReconciliationService service = new ReconciliationService();
        Transaction transactionA = Transaction.of("1", 1000, Currency.USD);
        // new for system a
        service.addFromSystemA(transactionA);
        Assertions.assertThat(service.getMismatches())
            .hasSize(1)
            .allMatch(m -> m.transactionId().equals(transactionA.id()));

        service.addFromSystemA(transactionA);
        Assertions.assertThat(service.getMismatches())
            .hasSize(1)
            .allMatch(m -> m.transactionId().equals(transactionA.id()));

        // new for system b
        Transaction transactionB = Transaction.of("2", 1000, Currency.USD);
        service.addFromSystemB(transactionB);
        Assertions.assertThat(service.getMismatches())
            .hasSize(2)
            .anyMatch(m -> m.transactionId().equals("2"));

        // reconcile for system a in system b
        service.addFromSystemB(transactionA);
        Assertions.assertThat(service.getMismatches())
            .hasSize(1)
            .noneMatch(m -> transactionA.id().equals(m.transactionId()));

        // don't match reconcile
        service.addFromSystemA(Transaction.of("2", 900, Currency.USD));
        Assertions.assertThat(service.getMismatches())
            .hasSize(1)
            .allMatch(m -> m.transactionId().equals(transactionB.id()));
    }

    @Test
    @DisplayName("race condition test")
    void shouldTestTwoConcurrent() throws InterruptedException {
        ReconciliationService service = new ReconciliationService();

        ExecutorService es = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(1);
        Transaction tx1 = Transaction.of("1", 1000, Currency.USD);

        es.submit(() -> {
            try {
                latch.await();
                service.addFromSystemA(tx1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        es.submit(() -> {
            try {
                latch.await();
                service.addFromSystemB(tx1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        es.shutdown();
        latch.countDown();

        boolean completed = es.awaitTermination(1, TimeUnit.SECONDS);
        Assertions.assertThat(completed).isTrue();
        Assertions.assertThat(service.getMismatches())
            .isEmpty();
    }
}
package com.leetcode.design.circuit.breaker;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class CircuitBreaker<T> {
    private final long coolDownPeriod;
    private final long halfOpenCalls;
    private final long failureThreshold;

    public CircuitBreaker(final long coolDownPeriod,
                          final long halfOpenCalls,
                          final long failureThreshold) {
        this.coolDownPeriod = coolDownPeriod;
        this.halfOpenCalls = halfOpenCalls;
        this.failureThreshold = failureThreshold;
    }

    private AtomicInteger halfOpenSuccess;
    private AtomicInteger halfOpenFails;
    private long lastFailureTime;
    private State state;
    ReentrantLock lock;

    /**
     * The Circuit Breaker has three main states:
     * 	1.	Closed –
     * The system operates normally. All requests pass through.
     * The breaker tracks failures, and if they exceed a threshold (e.g., 3 failures in a row), it “opens.”
     * 	2.	Open –
     * The breaker blocks all requests immediately — no attempt is made to contact the failing service.
     * After a cooldown period (timeout), it transitions to Half-Open.
     * 	3.	Half-Open –
     * The breaker allows a limited number of test requests.
     * 	•	If they succeed → the breaker resets to Closed.
     * 	•	If they fail → it switches back to Open.
     * @param supplier
     * @return
     */
    public T execute(Supplier<T> supplier) {
        switch (state) {
            case OPEN -> {
                long now = System.currentTimeMillis();
                if ((now - lastFailureTime) > coolDownPeriod) { // cooldown period is reached
                    moveToHalfOpen();
                } else {
                    throw new RuntimeException("Circuit breaker is OPEN. Call blocked.");
                }
            }
            case HALF_OPEN -> {
                if (halfOpenSuccess.get() > halfOpenCalls) {
                    moveToClosed();
                } else if (halfOpenFails.get() > halfOpenCalls) {
                    moveToOpen();
                }
            }
        }

        try {
            T result = supplier.get();
            onSuccess();
            return result;
        } catch (Exception e) {
            onFail();
            throw new RuntimeException(e);
        }
    }

    private void moveToHalfOpen() {
        state = State.HALF_OPEN;
    }

    private void moveToClosed() {
        state = State.CLOSED;


    }

    private void moveToOpen() {
        state = State.OPEN;
    }

    private void onSuccess() {
        halfOpenSuccess.getAndIncrement();
    }

    private void onFail() {
        halfOpenFails.incrementAndGet();
        lastFailureTime = System.currentTimeMillis();
        if (halfOpenFails.get() >= failureThreshold) {
            moveToOpen();
        }
    }
}

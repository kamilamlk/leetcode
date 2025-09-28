package com.leetcode.design.single.flight;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class SingleFlight {
    private final ConcurrentHashMap<Object, CompletableFuture<?>> inFlight;

    public SingleFlight() {
        this.inFlight = new ConcurrentHashMap<>();
    }

    public <K, V> CompletableFuture<V> getOrLoad(K key, Supplier<V> loader) {
        return (CompletableFuture<V>) inFlight.compute(key, (k, v) -> {
            if (v == null) {
                CompletableFuture<V> future = CompletableFuture.supplyAsync(loader);
                return future.whenComplete((r, ex) -> inFlight.remove(k));
            } else return v;
        });
    }
}

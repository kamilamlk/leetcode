package com.leetcode.design.event;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Broker broker = new Broker();
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            broker.getOrCreate("orders").redeliverUnacked(5000); // 5 sec timeout
        }, 5, 5, TimeUnit.SECONDS);
    }
}

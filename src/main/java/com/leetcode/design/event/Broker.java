package com.leetcode.design.event;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Broker {
    private final Map<String, Topic> broker;

    public Broker() {
        this.broker = new ConcurrentHashMap<>();
    }

    public Topic getOrCreate(String name) {
        return broker.computeIfAbsent(name, Topic::new);
    }
}

package com.leetcode.design.event;

import java.util.UUID;

public class Message {
    final String id;
    final String payload;
    final long timestamp;

    public Message(String payload) {
        this.id = UUID.randomUUID().toString();
        this.payload = payload;
        this.timestamp = System.currentTimeMillis();
    }
}
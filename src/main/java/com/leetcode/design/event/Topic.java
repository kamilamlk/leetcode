package com.leetcode.design.event;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Topic {
    String topic;
    BlockingQueue<Message> queue;
    Map<String, Message> inFlight;

    public Topic(String name) {
        this.topic = name;
        this.queue = new LinkedBlockingQueue<>();
        this.inFlight = new ConcurrentHashMap<>();
    }

    public void publish(Message message) {
        queue.add(message);
    }

    public Message poll() throws InterruptedException {
        Message message = queue.take();
        inFlight.put(message.id, message);
        return message;
    }

    public void ack(String msgId) {
        inFlight.remove(msgId);
    }

    // Redelivery (to simulate "at-least-once")
    public void redeliverUnacked(long timeoutMillis) {
        long now = System.currentTimeMillis();
        for (Message msg : inFlight.values()) {
            if (now - msg.timestamp > timeoutMillis) {
                System.out.println("🔄 Redelivering " + msg.id);
                queue.offer(new Message(msg.payload)); // requeue new copy
                inFlight.remove(msg.id);
            }
        }
    }
}

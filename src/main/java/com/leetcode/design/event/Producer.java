package com.leetcode.design.event;

class Producer {
    private final Broker broker;

    public Producer(Broker broker) {
        this.broker = broker;
    }

    public void send(String topicName, String payload) throws InterruptedException {
        broker.getOrCreate(topicName).publish(new Message(payload));
    }
}
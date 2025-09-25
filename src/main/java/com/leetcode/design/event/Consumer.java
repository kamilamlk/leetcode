package com.leetcode.design.event;

class Consumer {
    private final Topic topic;

    public Consumer(Broker broker, String topicName) {
        this.topic = broker.getOrCreate(topicName);
    }

    public Message poll() throws InterruptedException {
        return topic.poll();
    }

    public void ack(Message msg) {
        topic.ack(msg.id);
    }
}
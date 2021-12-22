package com.scottlogic;

import java.util.Objects;

public class Topic {
    private String topic;
    private int count;

    public Topic(String topic, int count) {
        this.topic = topic;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topic='" + topic + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic1 = (Topic) o;
        return count == topic1.count && topic.equals(topic1.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, count);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        this.count++;
    }
}

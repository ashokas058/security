package com.example.ashok_ray.security.Module_LEARN;

/**
 * Created by Ashok on 5/7/2019.
 */

public class learn_model {
    String head,topic,full_topic;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFull_topic() {
        return full_topic;
    }

    public void setFull_topic(String full_topic) {
        this.full_topic = full_topic;
    }

    public learn_model(String head, String topic, String full_topic) {

        this.head = head;
        this.topic = topic;
        this.full_topic = full_topic;
    }
}

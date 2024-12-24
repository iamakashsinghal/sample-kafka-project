package com.loma.kafka.payload;

/**
 * @author : Akash-loma
 * @project: kafka-CMS
 * @mailto : akash.singhal@lomatechnology.com
 * @created : 2024/8/14
 **/
public class KafkaMessageRequest {
    private String topic;
    private String message;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

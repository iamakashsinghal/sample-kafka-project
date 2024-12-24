package com.loma.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loma.kafka.dao.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author : Akash-loma
 * @project: kafka-CMS
 * @mailto : akash.singhal@lomatechnology.com
 * @created : 2024/8/14
 **/

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "#{'${kafka.topic.name}'.split(',')}", groupId = "group_id")
    public void listen(String message) {
        // Parse the message
        try {
            System.out.println(message);
            // Assign cases
        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
        }
    }
}

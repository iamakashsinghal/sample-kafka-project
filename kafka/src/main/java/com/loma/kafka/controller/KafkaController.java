package com.loma.kafka.controller;
import com.loma.kafka.payload.KafkaMessageRequest;
import com.loma.kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author : Akash-loma
 * @project: kafka-CMS
 * @mailto : akash.singhal@lomatechnology.com
 * @created : 2024/8/14
 **/
@RestController
public class KafkaController {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public KafkaController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/send")
    public String sendMessageToKafka(@RequestBody KafkaMessageRequest request) {
        try {
            kafkaProducerService.sendMessage(request.getTopic(), request.getMessage());
            return "Message sent to Kafka topic " + request.getTopic();
        } catch (Exception e) {
            return "Failed to send message: " + e.getMessage();
        }
    }
}
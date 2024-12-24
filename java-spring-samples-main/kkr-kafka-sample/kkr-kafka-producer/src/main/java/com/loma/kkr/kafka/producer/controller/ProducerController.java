package com.loma.kkr.kafka.producer.controller;

import com.loma.kkr.kafka.producer.component.EventMessageProducer;
import com.loma.kkr.kafka.producer.component.MessageProducer;
import com.loma.kkr.kafka.producer.domain.EventMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author akash
 */
@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final MessageProducer messageProducer;
    private final EventMessageProducer eventMessageProducer;

    @PostMapping("/sendMessage")
    public ResponseEntity<Map<String, Object>> sendMessage(@RequestParam("topic") String topic,
                                                           @RequestParam("key") String key,
                                                           @RequestParam("message") String message) {
        return ResponseEntity.ok(messageProducer.sendMessage(topic, key, message));
    }

    @PostMapping("/sendEventMessage")
    public ResponseEntity<Map<String, Object>> sendEventMessage(@RequestParam("topic") String topic,
                                                                @RequestParam("key") String key,
                                                                @RequestBody EventMessage eventMessage) {
        return ResponseEntity.ok(eventMessageProducer.sendEventMessage(topic, key, eventMessage));
    }

}

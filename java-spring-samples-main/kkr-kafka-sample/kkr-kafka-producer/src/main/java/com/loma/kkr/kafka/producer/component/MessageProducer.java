package com.loma.kkr.kafka.producer.component;

import com.loma.kkr.kafka.producer.exception.KafkaSendException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author akash
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public Map<String, Object> sendMessage(String topic, String key, String message) {
        CompletableFuture<SendResult<String, String>> resultFuture =  kafkaTemplate.send(topic, key, message);
        resultFuture.whenComplete((result, ex) -> {
            if (null != ex) {
                log.error("Unable to send message=[{}] due to : {}", message, ex.getMessage());
                throw new KafkaSendException(ex.getMessage());
            }
            log.info("Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
        });
        return Map.of("status", Boolean.TRUE,"message", "Message send successfully");
    }

}

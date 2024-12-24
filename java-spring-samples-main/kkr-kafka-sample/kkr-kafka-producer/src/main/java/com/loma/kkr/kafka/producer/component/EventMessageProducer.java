package com.loma.kkr.kafka.producer.component;

import com.loma.kkr.kafka.producer.domain.EventMessage;
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
public class EventMessageProducer {

    private final KafkaTemplate<String, EventMessage> kafkaTemplate;

    public Map<String, Object> sendEventMessage(String topic, String key, EventMessage eventMessage) {
        CompletableFuture<SendResult<String, EventMessage>> resultFuture =  kafkaTemplate.send(topic, key, eventMessage);
        resultFuture.whenComplete((result, ex) -> {
            if (null != ex) {
                log.error("Unable to send message=[{}] due to : {}", eventMessage, ex.getMessage());
                throw new KafkaSendException(ex.getMessage());
            }
            log.info("Sent message=[{}] with offset=[{}]", eventMessage, result.getRecordMetadata().offset());
        });
        return Map.of("status", Boolean.TRUE,"message", "Message send successfully");
    }

}

package com.loma.kkr.kafka.consumer.listener;

import com.loma.kkr.kafka.consumer.config.props.ConsumerTopicProps;
import com.loma.kkr.kafka.consumer.domain.EventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author akash
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EventMessageConsumer {

    private final ConsumerTopicProps topicProps;

    @KafkaListener(topics = "kkr-sample-events", containerFactory = "objectKafkaListenerContainerFactory")
    public void receiveEventMessage(EventMessage eventMessage) {
        log.info("consumer: topic event message: {}", eventMessage);
    }

}

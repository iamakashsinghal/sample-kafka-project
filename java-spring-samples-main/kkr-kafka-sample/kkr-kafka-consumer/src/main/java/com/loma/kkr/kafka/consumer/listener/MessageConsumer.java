//package com.loma.kkr.kafka.consumer.listener;
//
//import com.loma.kkr.kafka.consumer.config.props.ConsumerTopicProps;
//import com.loma.kkr.kafka.consumer.domain.EventMessage;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaHandler;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
///**
// * @author akash
// */
//@Slf4j
//@Component
//@RequiredArgsConstructor
//@KafkaListener(id = "kkr-sample-events-id", topics = "kkr-sample-events")
//public class MessageConsumer {
//
//    private final ConsumerTopicProps topicProps;
//
//    @KafkaListener(topics = "kkr-sample-events")
//    public void receiveMessage(String message) {
//        log.info("consumer: topic message: {}", message);
//    }
//
//}

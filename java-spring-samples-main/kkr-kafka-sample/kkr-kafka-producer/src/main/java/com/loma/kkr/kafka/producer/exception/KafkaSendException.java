package com.loma.kkr.kafka.producer.exception;

/**
 * @author akash
 */
public class KafkaSendException extends RuntimeException {

    public KafkaSendException(){}

    public KafkaSendException(String message){
        super(message);
    }

    public KafkaSendException(String message, Throwable cause){
        super(message, cause);
    }

}

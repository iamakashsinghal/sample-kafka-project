package com.loma.kkr.kafka.producer.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.Optional;

/**
 * @author akash
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({KafkaSendException.class})
    public ResponseEntity<Object> handleKafkaSendException(KafkaSendException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.of(Optional.of(Map.of("status", Boolean.FALSE, "message", ex.getMessage())));
    }

}

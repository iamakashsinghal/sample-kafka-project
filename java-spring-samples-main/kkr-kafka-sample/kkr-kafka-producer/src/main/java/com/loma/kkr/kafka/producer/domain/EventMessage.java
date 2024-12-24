package com.loma.kkr.kafka.producer.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author akash
 */
@Data
public class EventMessage {

    private Long eventMessageId;
    private String eventMessage;
    private LocalDateTime eventDateTime;
    private Boolean isEventActive;
    private BigDecimal eventValue;

}

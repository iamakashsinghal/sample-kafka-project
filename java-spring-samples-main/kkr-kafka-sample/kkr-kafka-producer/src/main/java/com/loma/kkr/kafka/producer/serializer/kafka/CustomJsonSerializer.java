package com.loma.kkr.kafka.producer.serializer.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loma.kkr.kafka.producer.config.ObjectMapperConfig;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.SerializationException;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

/**
 * @author akash
 * @param <T>
 */
public class CustomJsonSerializer<T> extends JsonSerializer<T> {

    private final ObjectMapper objectMapper;

    public CustomJsonSerializer() {
        this.objectMapper = new ObjectMapperConfig().objectMapper();
    }

    @Override
    public byte[] serialize(String topic, T data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing value", e);
        }
    }

}

package com.loma.kkr.kafka.producer.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author akash
 */
public class MultiDateTimeDeserializer extends StdDeserializer<LocalDateTime>  {

    private static final DateTimeFormatter[] DATE_FORMATTERS = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
    };

    public MultiDateTimeDeserializer() {
        this(null);
    }

    protected MultiDateTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        TreeNode treeNode = jp.getCodec().readTree(jp);
        TextNode textNode = treeNode instanceof TextNode
                ? (TextNode) treeNode
                : ((TextNode) treeNode.get(""));
        final String date = textNode.textValue();

        for (var formatter : DATE_FORMATTERS) {
            try {
                return LocalDateTime.parse(date, formatter);
            } catch (DateTimeParseException ignored) {
            }
        }
        throw new JsonParseException(jp, "Unparseable date: " + date + ". Supported formats: " +
                Arrays.stream(DATE_FORMATTERS).map(DateTimeFormatter::toString).collect(Collectors.joining("; ")));
    }
}

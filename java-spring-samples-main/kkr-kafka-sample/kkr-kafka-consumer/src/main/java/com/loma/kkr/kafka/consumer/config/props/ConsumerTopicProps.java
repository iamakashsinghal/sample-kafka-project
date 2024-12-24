package com.loma.kkr.kafka.consumer.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author akash
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "consumer")
public class ConsumerTopicProps {

    private List<String> topics;

}

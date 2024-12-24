package com.loma.kkr.webclient.config.props;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @author akash
 */
@Setter
@Getter
@ToString
@Configuration
@ConfigurationProperties(prefix = "web-client")
public class WebClientProps {

    private Connection connection;

    @Getter
    @Setter
    @ToString
    public static class Connection {
        private String serviceName;
        private Duration connectionTimeout;
        private Duration readTimeout;
        private Duration writeTimeout;
        private Duration responseTimeout;
    }

}

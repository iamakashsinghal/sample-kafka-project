package com.loma.kkr.webclient.config;


import com.loma.kkr.webclient.config.props.WebClientProps;
import com.loma.kkr.webclient.exception.ApiResponseException;
import com.loma.kkr.webclient.httpinterface.UserClient;
import io.netty.channel.ChannelOption;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.util.concurrent.TimeUnit;

/**
 * @author akash
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final WebClientProps wcConnProps;
    private final ReactorLoadBalancerExchangeFilterFunction lbFunction;

    @Bean
    @RefreshScope
    public WebClient webClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .clientConnector(reactorClientHttpConnector())
                .exchangeStrategies(exchangeStrategies())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .filter(lbFunction)
                .filter(exchangeFilterResponseProcessor())
                .build();
    }

    private ReactorClientHttpConnector reactorClientHttpConnector() {
        HttpClient httpClient = HttpClient.create()
                .wiretap("reactor.netty.http.client.HttpClient", LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL)
                // connection timeout in millis
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) wcConnProps.getConnection()
                        .getConnectionTimeout().toMillis())
                .responseTimeout(wcConnProps.getConnection().getResponseTimeout())
                .doOnConnected(connection ->
                        connection
                                .addHandlerLast(new ReadTimeoutHandler(
                                        wcConnProps.getConnection()
                                                .getReadTimeout().toMillis(), TimeUnit.MILLISECONDS)) // response read timeout in milliseconds
                                .addHandlerLast(new WriteTimeoutHandler(
                                        wcConnProps.getConnection()
                                                .getWriteTimeout().toMillis(), TimeUnit.MILLISECONDS))); //request write timeout in milliseconds
        return new ReactorClientHttpConnector(httpClient);
    }
    
    @Bean
    public ExchangeStrategies exchangeStrategies() {
        return ExchangeStrategies.builder()
                .codecs(c -> c.defaultCodecs()
                        .enableLoggingRequestDetails(true)
                ).build();
    }

    public static ExchangeFilterFunction exchangeFilterResponseProcessor() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().is5xxServerError()) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(errorBody -> Mono.error(new ApiResponseException(errorBody)));
            } else if (clientResponse.statusCode().is4xxClientError()) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(errorBody -> Mono.error(new ApiResponseException(errorBody)));
            } else {
                return Mono.just(clientResponse);
            }
        });
    }
    
    @Bean
    public UserClient userClient(WebClient.Builder webClientBuilder) {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(
                WebClientAdapter.forClient(webClient(webClientBuilder))).build();
        return httpServiceProxyFactory.createClient(UserClient.class);
    }
    
}

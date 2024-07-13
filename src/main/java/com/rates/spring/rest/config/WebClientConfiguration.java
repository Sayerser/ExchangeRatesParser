package com.rates.spring.rest.config;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import org.springframework.web.reactive.function.client.WebClient;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.logging.LogLevel;
import com.rates.spring.rest.property.RfcbProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.netty.transport.logging.AdvancedByteBufFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(WebClientConfiguration.class);
    private final RfcbProperties rfcbProperties;

    @Autowired
    public WebClientConfiguration(RfcbProperties rfcbProperties){
        this.rfcbProperties = rfcbProperties;
    }

    @Bean
    public WebClient webClient(){
        int timeout = rfcbProperties.getConnectionTimeout();

        ConnectionProvider connectionProvider = ConnectionProvider
                .builder("rfcb-connection-provider")
                .maxConnections(rfcbProperties.getMaxConnections())
                .build();

        HttpClient client = HttpClient.create(connectionProvider)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)
                .wiretap("reactor.netty.http.client.HttpClient",
                        LogLevel.TRACE,
                        AdvancedByteBufFormat.TEXTUAL,
                        Charset.forName("windows-1251"))
                .responseTimeout(Duration.ofSeconds(rfcbProperties.getResponseTimeout()))
                .doOnConnected(connection ->
                        connection.addHandlerLast(new ReadTimeoutHandler(timeout, TimeUnit.MILLISECONDS)));

        WebClient webClient = WebClient.builder()
                .baseUrl(rfcbProperties.getUrl())
                .clientConnector(new ReactorClientHttpConnector(client))
                .build();


        webClient = webClient.mutate()
                .filter((request, next) -> {
                    logger.info("Отправка запроса: {} {}", request.method(), request.url());
                    return next.exchange(request)
                            .doOnNext(response -> logger.info("Получен ответ: {} {}", response.statusCode(), response.headers().asHttpHeaders()));
                })
                .build();

        return webClient;
    }
}

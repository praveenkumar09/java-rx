package org.udemy.rx.dev.sec09.client;

import org.udemy.rx.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.scheduler.Schedulers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.function.Function;

public class ExternalServiceClient extends AbstractHttpClient {
    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> Flux<T> getDetails(String path, Function<String,T> constructor) {
        return httpClient.get()
                .uri(path)
                .responseContent()
                .asString()
                .map(constructor);
    }
}

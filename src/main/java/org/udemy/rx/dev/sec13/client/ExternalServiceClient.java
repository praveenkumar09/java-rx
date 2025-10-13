package org.udemy.rx.dev.sec13.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getBook(String path) {
        return httpClient.get()
                .uri(path)
                .responseContent()
                .asString()
                .startWith(RateLimiter.limitCalls())
                .contextWrite(UserService.getUserCategoryContext())
                .next();
    }
}
